package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getCustomer(@PathVariable Integer id, Model model) {

        model.addAttribute("customer", customerService.get(id));

        return "customer/viewcustomer";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {

        customerService.delete(id);

        return "redirect:/customer/list";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer/add";

    }

    @RequestMapping(method = RequestMethod.POST, path = "/add")
    public String persistCustomer(@ModelAttribute Customer customer) {

        Customer persistedCustomer = customerService.add(customer);


        return "redirect:/customer/" + persistedCustomer.getId();

    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{ID}")
    public String editCustomer(Model model,@PathVariable Integer ID) {

        model.addAttribute("customer", customerService.get(ID));

        return "customer/add";

    }


}