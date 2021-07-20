package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.jpa.HFManager;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * An {@link AccountService} implementation
 */
public class AccountServiceImpl implements AccountService {

    /**
     * @see AccountService#add(Account)
     */
    public void add(Account account) {

        EntityManager em = HFManager.getManager();
        em.getTransaction().begin();
        em.persist(account);
        em.getTransaction().commit();
        em.close();

    }

    /**
     * @see AccountService#deposit(int, double)
     */
    public void deposit(int id, double amount) {

        EntityManager em = HFManager.getManager();
        em.getTransaction().begin();
        AbstractAccount account = em.find(AbstractAccount.class,id);
        account.credit(amount);
        em.merge(account);
        em.getTransaction().commit();
        em.close();

    }

    /**
     * @see AccountService#withdraw(int, double)
     */
    public void withdraw(int id, double amount) {

        EntityManager em = HFManager.getManager();
        em.getTransaction().begin();
        AbstractAccount account = em.find(AbstractAccount.class, id);
        account.debit(amount);
        em.merge(account);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * @see AccountService#transfer(int, int, double)
     */

    public void transfer (int srcId, int dstId, double amount) {

        /*
        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }*/
    }
}
