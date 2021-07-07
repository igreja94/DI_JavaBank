package org.academiadecodigo.javabank.view;

import org.academiadecodigo.javabank.controller.BalanceController;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.model.account.Account;

import java.text.DecimalFormat;
import java.util.Set;

/**
 * A view used to show the customer balance
 * @see BalanceController
 */
public class BalanceView implements View {

    private DecimalFormat df = new DecimalFormat("#.##");
    private LoginController loginController;


    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showBalance();
    }

    private void showBalance() {

        Customer customer = loginController.getAuth().getAccessingCustomer();
        System.out.println("\n" + customer.getName() + Messages.VIEW_BALANCE_MESSAGE + "\n");

        Set<Account> accounts = customer.getAccounts();
        for (Account account : accounts) {
            System.out.println(account.getId() + "\t" + account.getAccountType() + "\t" + df.format(account.getBalance()));
        }

        System.out.println("\n\n" + Messages.VIEW_BALANCE_TOTAL_MESSAGE + df.format(customer.getBalance()));
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}
