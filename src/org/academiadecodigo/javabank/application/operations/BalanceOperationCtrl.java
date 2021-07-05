package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;
import org.academiadecodigo.javabank.domain.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * A bank operation to check the balance
 * @see BankOperationCtrl
 * @see UserOptions#GET_BALANCE
 */
public class BalanceOperationCtrl extends BankOperationCtrl {

    private DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Creates a new {@code BalanceOperation}
     *
     * //@see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public BalanceOperationCtrl(MainMenuCtrl menu) {
        super(menu);
    }

    /**
     * Executes this bank operation, printing the balance for the customer accounts
     *
     * @see Operation#execute()
     */
    @Override
    public void execute() {

        System.out.println("\n" + menu.getCustomer().getName() + Messages.BALANCE_MESSAGE + "\n");

        Set<Account> accounts = menu.getCustomer().getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.BALANCE_TOTAL_MESSAGE + df.format(menu.getCustomer().getBalance()));
    }
}
