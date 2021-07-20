package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.AbstractModel;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.JpaTransactionManager;
import org.academiadecodigo.javabank.persistence.dao.jpa.CustomerDaoImpl;
import org.academiadecodigo.javabank.services.CustomerService;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A JPA {@link CustomerService} implementation
 */
public class JpaCustomerService extends AbstractJpaService<Customer> implements CustomerService {

    public JpaCustomerService(JpaTransactionManager tm, CustomerDaoImpl dao) {
        super(tm,dao, Customer.class);
    }

    /**
     * @see CustomerService#getBalance(Integer)
     */
    @Override
    public double getBalance(Integer id) {

        tm.beginRead();

        try {

            Customer customer = Optional.ofNullable(tm.getEm().find(Customer.class, id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .mapToDouble(Account::getBalance)
                    .sum();

        } finally {
            tm.stopSession();
        }
    }

    /**
     * @see CustomerService#listCustomerAccountIds(Integer)
     */
    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        tm.beginRead();

        try {

            Customer customer = Optional.ofNullable(tm.getEm().find(Customer.class, id))
                    .orElseThrow(() -> new IllegalArgumentException("Customer does not exist"));

            return customer.getAccounts().stream()
                    .map(AbstractModel::getId)
                    .collect(Collectors.toSet());

        } finally {
            tm.stopSession();
        }

    }
}
