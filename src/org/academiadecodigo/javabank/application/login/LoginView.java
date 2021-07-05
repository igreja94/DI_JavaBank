package org.academiadecodigo.javabank.application.login;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.javabank.application.Messages;

import java.util.Set;


public class LoginView{

    private Prompt prompt;
    private IntegerSetInputScanner scanner;


    public LoginView() {

        prompt = new Prompt(System.in,System.out);

    }


    protected int scanCustomerId(Set <Integer> customerIDs ) {

        scanner = new IntegerSetInputScanner(customerIDs);
        scanner.setMessage(Messages.CHOOSE_CUSTOMER);
        scanner.setError(Messages.ERROR_INVALID_CUSTOMER);

        return prompt.getUserInput(scanner);

    }

}
