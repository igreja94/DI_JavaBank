package org.academiadecodigo.javabank.user;

import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;

public class Main {

    public static void main(String[] args) {

        AccountManager manager = new AccountManager();
        Bank bank = new Bank(manager);


        PromptMenu bankMenu = new PromptMenu(manager,bank);
        bankMenu.init(new Customer("Daniel"));

    }



}
