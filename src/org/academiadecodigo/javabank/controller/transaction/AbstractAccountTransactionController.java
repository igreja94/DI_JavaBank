package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.controller.AbstractController;
import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.services.AccountSrv;

/**
 * A generic account transaction controller to be used as a base for concrete transaction controller implementations
 * @see AbstractController
 * @see AccountTransactionController
 */
public abstract class AbstractAccountTransactionController extends AbstractController implements AccountTransactionController {

    protected AccountSrv accountSrv;


    public void setAccountSrv(AccountSrv accountSrv) {
        this.accountSrv = accountSrv;

    }

    public AccountSrv getAccountSrv() {
        return accountSrv;

    }
}
