package org.academiadecodigo.javabank.application.operations;

import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;

/**
 * A generic bank operation to be used as a base for concrete types of bank operations
 * @see Operation
 */
public abstract class BankOperationCtrl implements Operation {

    protected MainMenuCtrl menu;

    /**
     * Initializes a new {@code AbstractBankOperation} given a bank application
     *
     * //@param customer the bank application
     */
    public BankOperationCtrl(MainMenuCtrl menu) {
        this.menu = menu;
    }
}
