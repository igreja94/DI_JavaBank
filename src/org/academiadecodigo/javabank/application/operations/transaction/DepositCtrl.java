package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;

/**
 * An account transaction used to deposit an amount
 * @see AccountTransactionCtrl
 * @see UserOptions#DEPOSIT
 */
public class DepositCtrl extends AccountTransactionCtrl {

    /**
     * Initializes a new {@code DepositOperation}
     *
     * @see AccountTransactionCtrl
     */
    public DepositCtrl(MainMenuCtrl menu) {
        super(menu);
    }

    /**
     * Deposit an amount into an account
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
            accountManager.deposit(accountId, amount);
        }
    }
}
