package org.academiadecodigo.javabank.domain.account;

public class AccountFactory {

    public static Account openAccount(AccountType type, int id){

        switch (type) {
            case SAVINGS:
                return new SavingsAccount(id);

            case CHECKING:
                return new CheckingAccount(id);

        }
        return null;
    }
}
