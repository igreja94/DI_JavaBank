package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@Profile("dev")
public class CustomerController {

    CustomerServiceImpl customerService;

    // Map the URL/Verb to the method
    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public String initController(Model model) {

        List<Customer> customerList = customerService.getAllCustomers();

        model.addAttribute("customerList",customerList);

        // Return the view name
        return "showcustomers";

    }

    @Autowired
    public void setCustomerService(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }
}
