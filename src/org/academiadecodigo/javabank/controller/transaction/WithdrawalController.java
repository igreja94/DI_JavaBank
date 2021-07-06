package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.services.AccountSrv;

/**
 * A controller used for withdraw transactions
 * @see AbstractAccountTransactionController
 *
 */
public class WithdrawalController extends AbstractAccountTransactionController {


    /**
     * Withdraws an amount on the account with the given id
     *
     * @see AbstractAccountTransactionController#submitTransaction(int, double)
     */
    @Override
    public void submitTransaction(int accountId, double amount) {
        accountSrv.withdraw(accountId, amount);
    }



}
