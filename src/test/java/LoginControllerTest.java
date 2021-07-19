import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.controller.LoginController;
import org.academiadecodigo.javabank.services.AuthService;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.view.LoginView;
import org.academiadecodigo.javabank.view.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    private LoginController loginController;
    private View loginView;
    private AuthService authService;
    private Controller nextController;

    @Before
    public void setup() {

        //setup
        loginController = new LoginController();

        loginView = mock(View.class);
        authService = mock(AuthService.class);
        nextController = mock(Controller.class);

        loginController.setView(loginView);
        loginController.setAuthService(authService);
        loginController.setNextController(nextController);

    }

    @Test
    public void initTest(){

        //exercise
        loginController.init();

        //verify
        verify(loginView).show();

    }

    @Test
    public void onLoginSuccessefulTest(){

        int fakedId = 14256;

        when(authService.authenticate(fakedId)).thenReturn(true);

        //exercise
        loginController.onLogin(fakedId);

        //verify
        verify(authService).authenticate(fakedId);
        verify(nextController).init();
        verify(loginView, never()).show(); //verify if init after return statement executed, and therefore starting a new view;


    }

    @Test
    public void onLoginFailedTest() {

        int fakedId = 1;


        when(authService.authenticate(fakedId)).thenReturn(false);

        //exercise
        loginController.onLogin(fakedId);

        verify(authService).authenticate(fakedId);
        verify(loginView).show();
        Assert.assertTrue(loginController.isAuthFailed());

    }

}
