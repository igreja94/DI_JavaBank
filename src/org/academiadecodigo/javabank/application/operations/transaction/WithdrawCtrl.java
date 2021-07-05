package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;

/**
 * An account transaction used to withdraw an amount
 * @see AccountTransactionCtrl
 * @see UserOptions#WITHDRAW
 */
public class WithdrawCtrl extends AccountTransactionCtrl {

    /**
     * Initializes a new {@code WithdrawOperation}
     *
     * @see AccountTransactionCtrl
     */
    public WithdrawCtrl(MainMenuCtrl menu) {
        super(menu);
    }

    /**
     * Withdraw an amount from an account
     *
     * @see AccountTransactionCtrl#execute()
     */
    @Override
    public void execute() {

        super.execute();

        if (!hasAccounts()) {
            return;
        }

        Integer accountId = scanAccount();
        Double amount = scanAmount();

        if (menu.getCustomer().getAccountIds().contains(accountId)) {
            accountManager.withdraw(accountId, amount);
        }
    }
}
