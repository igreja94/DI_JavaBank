package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;
import org.academiadecodigo.javabank.application.operations.BankOperationCtrl;
import org.academiadecodigo.javabank.managers.AccountManager;

/**
 * A generic account transaction to be used as a base for concrete transaction implementations
 */
public abstract class AccountTransactionCtrl extends BankOperationCtrl {

    protected AccountManager accountManager;
    protected MainMenuCtrl menu;
    protected TransactionOperationView operationView;

    /**
     * Initializes a new {@code AbstractAccountTransactionOperation} given a bank application
     *
     * //@see AbstractBankOperation#AbstractBankOperation(BankApplication)
     */
    public AccountTransactionCtrl(MainMenuCtrl menu) {
        super(menu);
        this.menu = menu;
        accountManager = menu.getBank().getAccountManager();
        operationView = new TransactionOperationView(menu);
    }

    /**
     * Performs the transaction operation
     *
     * @see BankOperationCtrl#execute()
     * @see AccountTransactionCtrl#hasAccounts()
     */
    @Override
    public void execute() {

        if (!hasAccounts()) {
            System.out.println("\n" + Messages.ERROR_NO_ACCOUNT);
            return;
        }

        System.out.println("\n" + Messages.OPEN_ACCOUNTS + buildAccountList());
    }

    /**
     * Checks if customer has accounts
     *
     * @return {@code true} if the customer has accounts
     */
    protected boolean hasAccounts() {
        return menu.getCustomer().getAccountIds().size() > 0;
    }

    /**
     * Shows all the customer accounts
     *
     * @return the customer accounts
     */
    private String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : menu.getCustomer().getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    public Integer scanAccount(){

        return operationView.scanAccount();

    }

    public Double scanAmount(){

        return operationView.scanAmount();

    }



}
