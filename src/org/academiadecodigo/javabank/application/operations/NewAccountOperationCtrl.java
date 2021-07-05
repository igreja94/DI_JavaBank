package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;
import org.academiadecodigo.javabank.domain.account.AccountType;

/**
 * A bank operation to create new accounts
 * @see BankOperationCtrl
 * @see UserOptions#OPEN_ACCOUNT
 */
public class NewAccountOperationCtrl extends BankOperationCtrl {

    /**
     * Creates a new {@code NewAccountOperation}
     *
     * //@see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public NewAccountOperationCtrl(MainMenuCtrl menu) {
        super(menu);
    }

    /**
     * Creates a new bank account
     *
     * @see BankOperationCtrl#execute()
     */
    @Override
    public void execute() {

        int accountId = menu.getCustomer().openAccount(AccountType.CHECKING);

        System.out.println("\n" + Messages.CREATED_ACCOUNT + menu.getCustomer().getName() + " : " + accountId);
    }
}
