package org.academiadecodigo.javabank.user;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.precisiondouble.DoubleInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.academiadecodigo.javabank.domain.Bank;
import org.academiadecodigo.javabank.domain.Customer;
import org.academiadecodigo.javabank.domain.account.AccountType;
import org.academiadecodigo.javabank.managers.AccountManager;

import java.util.Set;

public class PromptMenu {

    private AccountManager accountManager;
    private Bank bank;
    private Customer customer;
    private Prompt prompt;
    private DoubleInputScanner doubleInput;
    private IntegerInputScanner intScanner;
    private StringInputScanner stringInput;
    private MenuInputScanner menuInput;


    // Prompt menu constructor

    public PromptMenu(AccountManager accountManager, Bank bank) {
        this.accountManager = accountManager;
        this.bank = bank;
    }

    // Initialize the main menu

    public void init(Customer customer) {

        this.customer = customer;

        //Initialize the prompt and its scanners

        prompt = new Prompt(System.in, System.out);
        doubleInput = new DoubleInputScanner();
        intScanner = new IntegerInputScanner();
        stringInput = new StringInputScanner();

        System.out.println("Welcome to Church National Bank");

        String[] yesOrNo = new String[]{"Yes", "No"};
        menuInput = new MenuInputScanner(yesOrNo);

        menuInput.setMessage("Is this user a client in our Bank?");
        int answer = prompt.getUserInput(menuInput);

        if (answer == 1) {

            System.out.println("Client registered as " + customer.getName() + ".");
            bank.addCustomer(customer);
            mainMenu();
            return;
        }

        System.exit(1);

    }


    public void mainMenu() {

        String[] menuOptions = new String[]{"Check Balance", "Deposit", "Withdraw", "Open new account", "Quit"};
        menuInput = new MenuInputScanner(menuOptions);

        menuInput.setMessage("Select operation");
        int answer = prompt.getUserInput(menuInput);

        switch (answer) {
            case 1:
                checkBalance();
                break;
            case 2:
                credit();
                break;
            case 3:
                debit();
                break;
            case 4:
                openAccount();
                break;
            case 5:
                quit();
                break;
            default:
                break;

        }

    }


    public void openAccount() {

        String[] options = new String[]{"Checking", "Savings"};
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("Which type of account would you like to create?");

        int answer = prompt.getUserInput(menuInput);

        switch (answer) {

            case 1:
                customer.openAccount(AccountType.CHECKING);
                System.out.println("Created new checking account!");
                mainMenu();
                break;
            case 2:
                customer.openAccount(AccountType.SAVINGS);
                System.out.println("Created new savings account");
                mainMenu();
                break;
            default:
                break;

        }

    }


    public void checkBalance() {

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("Which account would you like to check the balance?");

        if (options.length > 0) {

            int answer = prompt.getUserInput(menuInput);
            System.out.println(customer.getAccounts().get(answer).getBalance() + "€");
            mainMenu();
            return;

        }

        System.out.println("No accounts created");

    }


    public void debit() {

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to withdraw money from?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to withdraw?");
        double ammount = prompt.getUserInput(doubleInput);

        if (customer.getAccounts().get(answer).canDebit(ammount)) {

            customer.getAccounts().get(answer).debit(ammount);
            System.out.println("Withdraw completed: " + answer);
            System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + " €");
            mainMenu();
            return;

        }

        System.out.println("Operation not allowed");
        mainMenu();

    }


    public void credit() {

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to depoisit money on?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to deposit?");
        double ammount = prompt.getUserInput(doubleInput);

        if (customer.getAccounts().get(answer).canCredit(ammount)) {

            customer.getAccounts().get(answer).credit(ammount);

            System.out.println("Depoist completed: " + ammount);
            System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + " €");
            mainMenu();
            return;
        }

        System.out.println("Operation not allowed");
        mainMenu();

    }


    public void quit() {

        System.out.println("Leaving application!");
        System.exit(1);

    }

    public String[] getAccountsIDs(Customer customer) {

        System.out.println(customer.getName() + " accountsID in the bank:");
        Set<Integer> ids = customer.getAccounts().keySet();
        String[] accountsID = new String[ids.size()];
        int i = 0;

        for (Integer ID : ids) {
            accountsID[i] = "Account no. " + ID.toString();
            i++;
        }

        return accountsID;

    }

}