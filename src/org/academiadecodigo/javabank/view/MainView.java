package org.academiadecodigo.javabank.view;

import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.controller.MainController;

/**
 * A view used to display the main menu
 *
 * @see MainController
 */
public class MainView extends AbstractView {

    private MainController mainController;
    private LoginController loginController;

    /**
     * Sets the controller responsible for rendering the view
     *
     * @param mainController the controller to set
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * @see View#show()
     */
    @Override
    public void show() {
        showMenu();
    }

    private void showMenu() {

        MenuInputScanner scanner = new MenuInputScanner(UserOptions.getMessages());
        scanner.setError(Messages.VIEW_MAIN_ERROR);
        scanner.setMessage("\n" + Messages.VIEW_MAIN_MESSAGE + loginController.getAuth().getAccessingCustomer().getName());
        mainController.onMenuSelection(prompt.getUserInput(scanner));
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

}
