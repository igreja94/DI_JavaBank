package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.model.Bank;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthSrv;
import org.academiadecodigo.javabank.view.LoginView;

/**
 * The {@link LoginView} controller
 */
public class LoginController extends AbstractController {

    private Controller nextController;
    private AuthSrv auth;


    /**
     * Sets the next controller
     *
     * @param nextController the next controller to be set
     */
    public void setNextController(Controller nextController) {
        this.nextController = nextController;
    }


    public void setAuth(AuthSrv auth) {
        this.auth = auth;
    }

    /**
     * Identifies the logged in customer
     *
     * @param id the customer id
     */
    public void onLogin(int id) {
        auth.authenticate(id);
        nextController.init();
    }

    public AuthSrv getAuth() {
        return auth;
    }
}
