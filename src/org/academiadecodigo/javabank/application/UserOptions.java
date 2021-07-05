package org.academiadecodigo.javabank.application;

import org.academiadecodigo.javabank.application.operations.BalanceOperationCtrl;
import org.academiadecodigo.javabank.application.operations.NewAccountOperationCtrl;
import org.academiadecodigo.javabank.application.operations.Operation;
import org.academiadecodigo.javabank.application.operations.transaction.DepositCtrl;
import org.academiadecodigo.javabank.application.operations.transaction.WithdrawCtrl;

/**
 * The possible {@link Operation} types
 */
public enum UserOptions {

    /**
     * @see BalanceOperationCtrl
     */
    GET_BALANCE(1, Messages.MENU_GET_BALANCE),

    /**
     * @see DepositCtrl
     */
    DEPOSIT(2, Messages.MENU_DEPOSIT),

    /**
     * @see WithdrawCtrl
     */
    WITHDRAW(3, Messages.MENU_WITHDRAW),

    /**
     * @see NewAccountOperationCtrl
     */
    OPEN_ACCOUNT(4, Messages.MENU_OPEN_ACCOUNT),

    /**
     * User option to quit the application
     */
    QUIT(5, Messages.MENU_QUIT);

    private int option;
    private String message;

    UserOptions(int option, String message) {
        this.option = option;
        this.message = message;
    }

    /**
     * Gets the user option number
     *
     * @return the option number
     */
    public int getOption() {
        return option;
    }

    /**
     * Gets the message for the user option
     *
     * @return the user option message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Gets the messages for the options the user can perform
     *
     * @return Returns an array containing all the messages
     */
    public static String[] getMessages() {

        String[] messages = new String[values().length];

        for (UserOptions option : values()) {
            messages[option.getOption() - 1] = option.getMessage();
        }

        return messages;
    }
}
