package org.academiadecodigo.javabank.user;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.managers.AccountManager;
import sun.awt.image.IntegerInterleavedRaster;

public class PromptMenu {

    private AccountManager accountManager;
    private Bank bank;
    private Customer customer;
    private Prompt prompt;
    private DoubleInputScanner doubleInput;
    private IntegerInputScanner intScanner;
    private StringInputScanner stringInput;
    private MenuInputScanner menuInput;
    private boolean quit;


    // Prompt menu constructor

    public PromptMenu(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    // Initialize the main menu

    public void init(Customer customer){

        //Initialize the prompt and its scanners

        prompt = new Prompt(System.in,System.out);
        doubleInput = new DoubleInputScanner();
        intScanner = new IntegerInputScanner();
        stringInput = new StringInputScanner();

        System.out.println("Welcome to Church National Bank");

        String[] yesOrNo = new String[]{"Yes","No"};
        menuInput = new MenuInputScanner(yesOrNo);

        menuInput.setMessage("Is this user a client in our Bank");
        int answer = prompt.getUserInput(menuInput);

        if (answer == 1) {

            System.out.println("We're glad to have you as our customer, " + customer.getName() + ".");
            joinBank(customer);
            mainMenu();
            return;
        }

        System.exit(0);

    }


    public void joinBank(Customer customer){

        bank.addCustomer(customer);


    }

    public void mainMenu(){

        String[] menuOptions = new String[]{"Check Balance","Deposit","Withdraw","Open new account","Quit"};
        menuInput = new MenuInputScanner(menuOptions);

        menuInput.setMessage("Select operation");
        int answer = prompt.getUserInput(menuInput);

        switch (answer) {
            case 0:
                checkBalance();
                break;
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                openAccount();
                break;
            case 4:
                quit();
                break;
        }


    }

    public void openAccount(){
        mainMenu();
    }

    public void checkBalance(){
        mainMenu();
    }

    public void withdraw(){
        mainMenu();
    }

    public void deposit(){
        mainMenu();
    }

    public void quit(){

    }

}
