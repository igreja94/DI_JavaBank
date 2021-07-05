package org.academiadecodigo.javabank.application.mainmenu;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.application.Messages;
import org.academiadecodigo.javabank.application.UserOptions;

public class MainMenuView {

    MenuInputScanner mainMenu;
    Prompt prompt;

    MainMenuView(){

        prompt = new Prompt(System.in,System.out);
        buildMainMenu();

    }

    protected void buildMainMenu() {

        mainMenu = new MenuInputScanner(UserOptions.getMessages());
        mainMenu.setError(Messages.ERROR_INVALID_OPTION);
        mainMenu.setMessage(Messages.MENU_WELCOME);

    }

    protected Integer getMenuOption() {

        return prompt.getUserInput(mainMenu);

    }

    public Prompt getPrompt() {

        return prompt;

    }
}
