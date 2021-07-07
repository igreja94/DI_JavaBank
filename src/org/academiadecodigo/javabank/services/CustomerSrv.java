package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerSrv implements CustomerService{

    private Bank bank;

    @Override
    public Customer get(Integer id) {

        return bank.getCustomers().get(id);

    }

    @Override
    public List<Customer> list() {

        ArrayList<Customer> customerList = new ArrayList<>(bank.getCustomers().values()); //add all in constructor
        return customerList;

    }

    @Override
    public Set<Integer> listCustomerAccountIds(Integer id) {

        HashSet<Integer> customerAccounts = new HashSet<>(list().get(id).getAccountIds()); //add all in constructor
        return customerAccounts;

    }

    @Override
    public double getBalance(int customerId) {
        return list().get(customerId).getBalance();
    }

    @Override
    public void add(Customer customer) {
        bank.addCustomer(customer);
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Set<Integer> getAllCustomerIDs(){
        return bank.getCustomers().keySet();
    }


}
