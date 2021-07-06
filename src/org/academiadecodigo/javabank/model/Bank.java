package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.HashMap;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private HashMap<Integer, Customer> customers;

    /**
     * Creates a new instance of Bank
     */
    public Bank() {
        this.customers = new HashMap<>();
    }

    public HashMap<Integer, Customer> getCustomers() {

        return customers;
    }


    public void addCustomer(Customer customer) {
        customers.put(customer.getId(), customer);
    }
}
