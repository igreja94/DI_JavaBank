package org.academiadecodigo.javabank;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.controller.*;
import org.academiadecodigo.javabank.controller.transaction.DepositController;
import org.academiadecodigo.javabank.controller.transaction.WithdrawalController;
import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AccountSrv;
import org.academiadecodigo.javabank.services.AuthSrv;
import org.academiadecodigo.javabank.services.CustomerSrv;
import org.academiadecodigo.javabank.view.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for wiring the objects dependencies
 */
public class Bootstrap {

    /**
     * Creates a {@code Bank} and populates it with data
     *
     * @return the bank
     */
    public Bank generateTestData() {

        Bank bank = new Bank();

        Customer c1 = new Customer(1, "Rui");
        Customer c2 = new Customer(2, "Sergio");
        Customer c3 = new Customer(3, "Bruno");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);

        return bank;
    }

    /**
     * Wires the necessary object dependencies
     *
     * @param bank the bank to wire
     * @return the login controller
     */
    public LoginController wireObjects(Bank bank) {

        // init all services
        CustomerSrv customerSrv = new CustomerSrv();
        customerSrv.setBank(bank);
        AuthSrv authSrv = new AuthSrv();
        authSrv.setCustomerSrv(customerSrv);
        AccountSrv accountSrv = new AccountSrv();


        // attach all input to standard i/o
        Prompt prompt = new Prompt(System.in, System.out);

        // wire login controller and view
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setAuth(authSrv);
        loginView.setLoginController(loginController);
        loginView.setPrompt(prompt);

        // wire main controller and view
        MainController mainController = new MainController();
        MainView mainView = new MainView();
        mainView.setMainController(mainController);
        mainView.setLoginController(loginController);
        mainView.setPrompt(prompt);
        mainController.setView(mainView);
        loginController.setNextController(mainController);

        // wire balance controller and view
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceView.setLoginController(loginController);
        balanceController.setView(balanceView);

        // wire new account controller and view
        NewAccountView newAccountView = new NewAccountView();
        NewAccountController newAccountController = new NewAccountController();
        newAccountController.setAccountSrv(accountSrv);
        newAccountController.setAuthSrv(authSrv);
        newAccountController.setView(newAccountView);
        newAccountView.setNewAccountController(newAccountController);

        // wire account transactions controllers and views
        DepositController depositController = new DepositController();
        depositController.setAccountSrv(accountSrv);
        WithdrawalController withdrawalController = new WithdrawalController();
        withdrawalController.setAccountSrv(accountSrv);
        AccountTransactionView depositView = new AccountTransactionView();
        depositView.setLoginController(loginController);
        AccountTransactionView withdrawView = new AccountTransactionView();
        withdrawView.setLoginController(loginController);
        depositController.setView(depositView);
        withdrawalController.setView(withdrawView);
        depositView.setPrompt(prompt);
        depositView.setTransactionController(depositController);
        withdrawView.setPrompt(prompt);
        withdrawView.setTransactionController(withdrawalController);

        // setup the controller map
        Map<Integer, Controller> controllerMap = new HashMap<>();
        controllerMap.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        controllerMap.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        controllerMap.put(UserOptions.DEPOSIT.getOption(), depositController);
        controllerMap.put(UserOptions.WITHDRAW.getOption(), withdrawalController);

        mainController.setControllerMap(controllerMap);

        return loginController;
    }
}
