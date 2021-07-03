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

import java.util.HashSet;
import java.util.Set;

public class PromptMenu {

    private AccountManager accountManager;
    private Bank bank;
    private Bank.Filer filer;
    private Prompt prompt;
    private DoubleInputScanner doubleInput;
    private MenuInputScanner menuInput;



    // Prompt menu constructor

    public PromptMenu(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
        this.filer = bank.new Filer();
    }

    // Initialize the main menu

    public void init(){

        //Initialize the prompt and its scanners

        prompt = new Prompt(System.in,System.out);
        doubleInput = new DoubleInputScanner();

        System.out.println("Welcome to Church National Bank");

        String[] yesOrNo = new String[]{"Yes","No"};
        menuInput = new MenuInputScanner(yesOrNo);

        menuInput.setMessage("Would you like to load the customer database?");
        int answer = prompt.getUserInput(menuInput);

        if (answer == 1) {
            bank.setCustomers(filer.loadFromDatabase());
            mainMenu(selectCustomer());

        }
        mainMenu(new Customer("Daniel"));
    }

    public Customer selectCustomer(){

        System.out.println("Select customer to manage");

        Set<Customer> customers = bank.getCustomers();
        String[] customerNames = new String[customers.size()];
        int i = 0;

        for (Customer customer: customers) {
            customerNames[i] = customer.getName();
           i++;
        }

        menuInput = new MenuInputScanner(customerNames);
        int answer = prompt.getUserInput(menuInput);
        int j = 0;

        for (Customer customer: customers) {
            if (customer.getName().equals(customerNames[i])) {
                return customer;
            }
        }
        return null;
    }


    public void mainMenu(Customer customer){

        String[] options = new String[]{"Check Balance","Deposit","Withdraw","Open new account","Quit"};
        menuInput = new MenuInputScanner(options);

        menuInput.setMessage("Select operation");
        int answer = prompt.getUserInput(menuInput);

        switch (answer) {
            case 1:
                checkBalance(customer);
                break;
            case 2:
                credit(customer);
                break;
            case 3:
                debit(customer);
                break;
            case 4:
                openAccount(customer);
                break;
            case 5:
                quit();
                break;
            default:
                break;
        }

    }

    public void openAccount(Customer customer){

        String[] options = new String[]{"Checking","Savings"};
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("Which type of account would you like to create?");

        int answer = prompt.getUserInput(menuInput);

        switch (answer) {

            case 1:
                customer.openAccount(AccountType.CHECKING);
                System.out.println("Created new checking account!");
                mainMenu(customer);
                break;
            case 2:
                customer.openAccount(AccountType.SAVINGS);
                System.out.println("Created new savings account");
                mainMenu(customer);
                break;
            default:
                break;
        }
    }

    public void checkBalance(Customer customer){

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("Which account would you like to check the balance?");

        int answer = prompt.getUserInput(menuInput);

        System.out.println(customer.getAccounts().get(answer).getBalance() + "€");

        mainMenu(customer);

    }

    public void debit(Customer customer){

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to withdraw money from?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to deposit?");
        double ammount = prompt.getUserInput(doubleInput);

        customer.getAccounts().get(answer).debit(ammount);

        System.out.println("Withdraw completed: " + answer);
        System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + "€" );

        mainMenu(customer);

    }

    public void credit(Customer customer){

        String[] options = getAccountsIDs(customer);
        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to withdraw money from?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to deposit?");
        double ammount = prompt.getUserInput(doubleInput);

        customer.getAccounts().get(answer).credit(ammount);

        System.out.println("Depoist completed: " + ammount);
        System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + "€" );

        mainMenu(customer);

    }

    public void quit(){

        System.exit(1);

    }

    public String[] getAccountsIDs(Customer customer){

        System.out.println(customer.getName() + " accountsID in the bank:");
        Set<Integer> ids =  customer.getAccounts().keySet();
        String[] accountsID = new String[ids.size()];
        int i = 0;

        for (Integer ID : ids) {
            accountsID[i] = "Account no. " + ID.toString();
            i++;
        }

        return accountsID;

    }



}
