package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.jpa.HFManager;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.*;

/**
 * An {@link CustomerService} implementation
 */
public class CustomerServiceImpl implements CustomerService {

    /**
     * @see CustomerService#get(Integer)
     */
    @Override
    public Customer get(Integer id) {

        EntityManager em = HFManager.getManager();

        try {
            return em.find(Customer.class, id);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * @see CustomerService#list()
     */
    @Override
    public List<Customer> list() {
        return null;
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {


        EntityManager em = HFManager.getManager();

        Customer customer = em.find(Customer.class,id);

        Set<Integer> accoundIds = new HashSet<>();

        for (AbstractAccount account : customer.getAccounts()) {
            accoundIds.add(account.getId());
        }

        em.close();

        return accoundIds;

    }

    /**
     * @see CustomerService#getBalance(int)
     */
    @Override
    public double getBalance(int id) {

        EntityManager em = HFManager.getManager();

        TypedQuery<AbstractAccount> query =
                em.createQuery("SELECT account FROM Account account WHERE account.customer.id = :id", AbstractAccount.class);

        query.setParameter("id",id);

        List<AbstractAccount> accounts = query.getResultList();

        double balance = 0;
        for (AbstractAccount account : accounts) {
            balance += account.getBalance();
        }

        return balance;

    }

    /**
     * @see CustomerService#add(Customer)
     */
    @Override
    public void add(Customer customer) {

        EntityManager em = HFManager.getManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();

    }
}
