package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;

public class AuthSrv implements AuthService{


    private CustomerSrv customerSrv;
    private int id;

    @Override
    public boolean authenticate(Integer id) {

        this.id = id;
        return customerSrv.list().contains(customerSrv.get(id));

    }

    @Override
    public Customer getAccessingCustomer() {
        return customerSrv.get(id);
    }

    public void setCustomerSrv(CustomerSrv customerSrv) {
        this.customerSrv = customerSrv;
    }

    public CustomerSrv getCustomerSrv() {
        return customerSrv;
    }
}
