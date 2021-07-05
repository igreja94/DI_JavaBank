package org.academiadecodigo.javabank.application.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.application.operations.BalanceOperationCtrl;
import org.academiadecodigo.javabank.application.operations.NewAccountOperationCtrl;
import org.academiadecodigo.javabank.application.operations.Operation;
import org.academiadecodigo.javabank.application.operations.transaction.DepositCtrl;
import org.academiadecodigo.javabank.application.operations.transaction.WithdrawCtrl;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;

import java.util.HashMap;
import java.util.Map;

public class MainMenuCtrl {

    private MainMenuView mainMenuView;
    private Integer userOption;
    private Bank bank;
    private Customer customer;
    private Map<Integer, Operation> operationMap;



    public void init() {

        mainMenuView = new MainMenuView();
        userOption = mainMenuView.getMenuOption();
        operationMap = buildOperationsMap();
        menuLoop();

    }

    public void menuLoop() {

        int userChoice = mainMenuView.getMenuOption();

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationMap.get(userChoice).execute();
        menuLoop();

    }


    public Customer getCustomer() {

        return customer;

    }

    public void setBank(Bank bank) {

        this.bank = bank;

    }

    private Map<Integer, Operation> buildOperationsMap() {

        Map<Integer, Operation> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), new BalanceOperationCtrl(this));
        map.put(UserOptions.DEPOSIT.getOption(), new DepositCtrl(this));
        map.put(UserOptions.WITHDRAW.getOption(), new WithdrawCtrl(this));
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), new NewAccountOperationCtrl(this));

        return map;

    }

    public void setCustomer(Customer customer) {

        this.customer = customer;

    }

     public Prompt getPrompt(){

        return mainMenuView.getPrompt();

     }

    public Bank getBank() {

        return bank;

    }
}
