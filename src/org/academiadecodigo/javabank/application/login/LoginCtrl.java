package org.academiadecodigo.javabank.application.login;

import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

import java.util.Set;

public class LoginCtrl {

    private LoginView loginView;
    private MainMenuCtrl mainMenuCtrl;
    private Bank bank;
    private Integer id;

    public LoginCtrl(Bank bank){
        this.bank = bank;
    }


    public void init(){

        loginView = new LoginView();
        id = loginView.scanCustomerId(getCustomerID());
        mainMenuCtrl = new MainMenuCtrl();
        mainMenuCtrl.setBank(bank);
        mainMenuCtrl.setCustomer(bank.getCustomer(id));
        mainMenuCtrl.init();

    }


    protected Set<Integer> getCustomerID(){

        return bank.getCustomerIds();

    }





}
