package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import java.util.HashMap;
import java.util.Map;

public class AccountSrv implements AccountService{


    private Map<Integer,Account> accountMap;


    public AccountSrv(){

        accountMap = new HashMap<>();

    }

    @Override
    public void add(Account account) {

        accountMap.put(account.getId(),account);

    }

    @Override
    public void deposit(int id, double amount) {

        accountMap.get(id).credit(amount);

    }

    @Override
    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }

    }

}
