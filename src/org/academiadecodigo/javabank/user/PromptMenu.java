package org.academiadecodigo.javabank.user;

import org.academiadecodigo.bootcamp.Prompt;
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
    private Prompt prompt;
    private DoubleInputScanner doubleInput;
    private MenuInputScanner menuInput;
    private StringInputScanner stringInput;



    // Prompt menu constructor

    public PromptMenu(AccountManager accountManager, Bank bank){
        this.accountManager = accountManager;
        this.bank = bank;
    }

    // Initialize the main menu

    public void init(){

        //Initialize the prompt and its scanners

        prompt = new Prompt(System.in,System.out);
        doubleInput = new DoubleInputScanner();

        System.out.println("Welcome to Church National Bank");

        String[] yesOrNo = new String[]{"Yes","No"};
        menuInput = new MenuInputScanner(yesOrNo);

        menuInput.setMessage("Would you like to create a new customer?");
        int answer = prompt.getUserInput(menuInput);

        if (answer == 1) {

            stringInput = new StringInputScanner();
            stringInput.setMessage("What is the name of the customer");

            Customer customer1 = new Customer(prompt.getUserInput(stringInput));
            bank.addCustomer(customer1);
            mainMenu(customer1);
        }
        System.exit(1);

    }


    public void mainMenu(Customer customer){
        System.out.println("Managing customer: " + customer.getName());

        String[] options = new String[]{"Check Balance","Deposit","Withdraw","Create account","Manage Customers","Quit"};
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
                customerManagement();
                break;
            case 6:
                quit();
            default:
                break;
        }

    }


    public void checkBalance(Customer customer){

        String[] options = getAccountsIDs(customer);

        if ( options.length <= 0) {
            System.out.println("You have no accounts");
            mainMenu(customer);
        }

        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("Which account would you like to check the balance?");

        int answer = prompt.getUserInput(menuInput);

        System.out.println("Balance: " + customer.getAccounts().get(answer).getBalance() + "€");

        mainMenu(customer);

    }


    public void credit(Customer customer){

        String[] options = getAccountsIDs(customer);

        if ( options.length <= 0) {
            System.out.println("You have no accounts");
            mainMenu(customer);
        }

        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to deposit money?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to deposit?");
        double ammount = prompt.getUserInput(doubleInput);

        customer.getAccounts().get(answer).credit(ammount);

        System.out.println("Depoist completed: " + ammount);
        System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + "€" );

        mainMenu(customer);

    }


    public void debit(Customer customer){

        String[] options = getAccountsIDs(customer);

        if ( options.length <= 0) {
            System.out.println("You have no accounts");
            mainMenu(customer);
        }

        menuInput = new MenuInputScanner(options);
        menuInput.setMessage("In which account would you like to withdraw money from?");

        int answer = prompt.getUserInput(menuInput);

        doubleInput.setMessage("How much money would you like to withdraw?");
        double ammount = prompt.getUserInput(doubleInput);

        customer.getAccounts().get(answer).debit(ammount);

        System.out.println("Withdraw completed: " + answer);
        System.out.println("New balance is " + customer.getAccounts().get(answer).getBalance() + "€" );

        mainMenu(customer);

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



    public void quit(){

        System.exit(1);

    }


    public String[] getAccountsIDs(Customer customer){

        Set<Integer> ids =  customer.getAccounts().keySet();
        String[] accountsID = new String[ids.size()];

        int i = 0;
        for (Integer ID : ids) {
            accountsID[i] = "Account no." + ID.toString() + "; " + customer.getAccounts().get(ID).getAccountType();
            i++;
        }

        return accountsID;

    }

    public void customerManagement() {

        System.out.println("What operation would you like to do");

        String[] options = new String[]{"Add new customer","Change customer","Delete costumer"};
        menuInput = new MenuInputScanner(options);

        menuInput.setMessage("Select operation");
        int answer = prompt.getUserInput(menuInput);

        switch (answer) {

            case 1:
                addCustomer();
                break;
            case 2:
                mainMenu(selectCustomer());
                break;
            case 3:
                deleteCustomer();
        }


    }

    private void addCustomer() {

        stringInput.setMessage("What is the name of the customer");
        bank.addCustomer(new Customer(prompt.getUserInput(stringInput)));
        mainMenu(selectCustomer());

    }


    public Customer selectCustomer(){

        String[] customerNames = new String[bank.getCustomers().size()];

        System.out.println("Select customer to manage");

        int i = 0;
        for (Customer customer: bank.getCustomers()) {
            customerNames[i] = customer.getName();
            i++;
        }

        menuInput = new MenuInputScanner(customerNames);
        int answer = prompt.getUserInput(menuInput);
        int j = 0;

        for (Customer customer: bank.getCustomers()) {
            if (customer.getName().equals(customerNames[answer - 1])) {
                return customer;
            }
        }
        return null;
    }


    private void deleteCustomer() {


        String[] customerNames = new String[bank.getCustomers().size()];


        if ( customerNames.length <= 1) {
            System.out.println("You need at least 1 customer active");
            mainMenu(selectCustomer());
        }

        System.out.println("Select customer to delete");

        int i = 0;
        for (Customer customer: bank.getCustomers()) {
            customerNames[i] = customer.getName();
            i++;
        }

        menuInput = new MenuInputScanner(customerNames);
        int answer = prompt.getUserInput(menuInput);
        int j = 0;

        for (Customer customer: bank.getCustomers()) {

            if (customer.getName().equals(customerNames[answer - 1])) {
                bank.getCustomers().remove(customer);
                System.out.println(customer.getName() + " removed from the bank");
            }

        }

        mainMenu(selectCustomer());

    }

}
