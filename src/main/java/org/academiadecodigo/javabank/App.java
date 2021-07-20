package org.academiadecodigo.javabank;

import org.academiadecodigo.javabank.controller.Controller;
import org.academiadecodigo.javabank.persistence.*;
import org.academiadecodigo.javabank.persistence.dao.jpa.AccountDao;
import org.academiadecodigo.javabank.persistence.dao.jpa.CustomerDao;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.jpa.JpaAccountService;
import org.academiadecodigo.javabank.services.jpa.JpaCustomerService;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        JpaBootstrap jpa = new JpaBootstrap();
        EntityManagerFactory emf = jpa.start();

        App app = new App();
        app.bootStrap(emf);

        jpa.stop();

    }

    private void bootStrap(EntityManagerFactory emf) {

        Bootstrap bootstrap = new Bootstrap();

        JpaSessionManager sm = new JpaSessionManager();
        sm.setEmf(emf);
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setSm(sm);
        AccountDao accDao = new AccountDao();
        accDao.setSm(sm);
        CustomerDao cusDao = new CustomerDao();
        cusDao.setSm(sm);


        bootstrap.setAuthService(new AuthServiceImpl());
        bootstrap.setAccountService(new JpaAccountService(tm,accDao));
        bootstrap.setCustomerService(new JpaCustomerService(tm,cusDao));

        Controller controller = bootstrap.wireObjects();

        // start application
        controller.init();
    }
}
