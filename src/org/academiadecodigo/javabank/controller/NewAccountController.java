package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.managers.AccountManager;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;
import org.academiadecodigo.javabank.services.AccountSrv;
import org.academiadecodigo.javabank.services.AuthSrv;
import org.academiadecodigo.javabank.services.CustomerSrv;
import org.academiadecodigo.javabank.view.NewAccountView;

/**
 * The {@link NewAccountView} controller
 */
public class NewAccountController extends AbstractController {


    private Integer newAccountId;
    private AccountFactory accountFactory;
    private AccountSrv accountSrv;
    private AuthSrv authSrv;


    public NewAccountController(){
        accountFactory = new AccountFactory();
    }



    /**
     * Gets the new account id
     *
     * @return the new account id
     */
    public Integer getNewAccountId() {
        return newAccountId;
    }

    /**
     * Creates a new {@link Account}
     *
     * @see Controller#init()
     * @see AccountManager#openAccount(AccountType)
     */
    @Override
    public void init() {

        newAccountId = createAccount();
        super.init();
    }

    private int createAccount() {

        Account newAccount = accountFactory.createAccount(AccountType.CHECKING);
        accountSrv.add(newAccount);
        authSrv.getAccessingCustomer().addAccount(newAccount);

        return newAccount.getId();
    }


    public void setAccountSrv(AccountSrv accountSrv) {
        this.accountSrv = accountSrv;
    }

    public void setAuthSrv(AuthSrv authSrv) {
        this.authSrv = authSrv;
    }
}
