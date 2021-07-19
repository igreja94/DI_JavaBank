package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
@Entity
public class Customer extends AbstractModel {

    private String name;

    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            // use Category foreign key on Product table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "customer"
    )
    private List<AbstractAccount> accounts = new ArrayList<>();

    /**
     * Gets the name of the customer
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public List<AbstractAccount> getAccounts() {
        return accounts;
    }

    /**
     * Adds a new account to the customer
     *
     * @param account the account to add
     */
    public void addAccount(AbstractAccount account) {
        accounts.add(account);
        account.setCustomer(this);
    }

    /**
     * Removes an account from the customer
     *
     * @param account the account to remove
     */
    public void removeAccount(AbstractAccount account) {
        accounts.remove(account);
    }
}


