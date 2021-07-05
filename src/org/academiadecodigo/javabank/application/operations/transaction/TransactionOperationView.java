package org.academiadecodigo.javabank.application.operations.transaction;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.mainmenu.MainMenuCtrl;

public class TransactionOperationView {

    private MainMenuCtrl menu;

    public TransactionOperationView(MainMenuCtrl menu) {
        this.menu = menu;
    }

    protected int scanAccount() {
        IntegerSetInputScanner scanner = new IntegerSetInputScanner(menu.getCustomer().getAccountIds());
        scanner.setMessage(Messages.CHOOSE_ACCOUNT);
        scanner.setError(Messages.ERROR_INVALID_ACCOUNT);

        return menu.getPrompt().getUserInput(scanner);
    }

    /**
     * Prompts the user for a transaction amount
     *
     * @return the amount to be transactioned
     */
    protected double scanAmount() {
        DoubleInputScanner scanner = new DoubleInputScanner();
        scanner.setMessage(Messages.CHOOSE_AMOUNT);
        scanner.setError(Messages.ERROR_INVALID_AMOUNT);

        return menu.getPrompt().getUserInput(scanner);
    }


}
