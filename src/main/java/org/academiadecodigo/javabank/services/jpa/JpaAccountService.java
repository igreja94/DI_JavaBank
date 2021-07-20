package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.JpaSessionManager;
import org.academiadecodigo.javabank.persistence.JpaTransactionManager;
import org.academiadecodigo.javabank.persistence.dao.jpa.AccountDao;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * A JPA {@link AccountService} implementation
 */
public class JpaAccountService extends AbstractJpaService<Account> implements AccountService {


    public JpaAccountService(JpaTransactionManager tm,AccountDao dao) {
        super(tm,dao, Account.class);
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    @Override
    public void deposit(Integer id, double amount) {

        tm.beginRead();

        try {

            tm.beginWrite();

            Optional<Account> account = Optional.ofNullable(dao.findById(Account.class,id));

            if (!account.isPresent()) {
                tm.rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).credit(amount);

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        } finally {

            tm.stopSession();

        }
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        tm.beginRead();

        try {

            tm.beginWrite();

            Optional<Account> account = Optional.ofNullable(dao.findById(Account.class,id));

            if (!account.isPresent()) {
                tm.rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).debit(amount);

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        } finally {

            tm.stopSession();
        }
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        tm.beginRead();

        try {

            tm.beginWrite();

            Optional<Account> srcAccount = Optional.ofNullable(dao.findById(Account.class,srcId));
            Optional<Account> dstAccount = Optional.ofNullable(dao.findById(Account.class,dstId));

            if (!srcAccount.isPresent() || !dstAccount.isPresent()) {
                tm.rollback();
            }

            srcAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));
            dstAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));

            // make sure transaction can be performed
            if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
                srcAccount.get().debit(amount);
                dstAccount.get().credit(amount);
            }

            tm.commit();

        } catch (RollbackException ex) {

            tm.rollback();

        } finally {

            tm.stopSession();
        }
    }
}
