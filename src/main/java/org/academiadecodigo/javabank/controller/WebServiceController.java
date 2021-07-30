package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.command.AccountDto;
import org.academiadecodigo.javabank.command.CustomerDto;
import org.academiadecodigo.javabank.converters.AccountToAccountDto;
import org.academiadecodigo.javabank.converters.CustomerDtoToCustomer;
import org.academiadecodigo.javabank.converters.CustomerToCustomerDto;
import org.academiadecodigo.javabank.exceptions.JavaBankException;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class WebServiceController {

    private CustomerDtoToCustomer dto2cust;
    private CustomerToCustomerDto cust2dto;
    private AccountToAccountDto acc2dto;
    private CustomerService customerService;


    @Autowired
    public void setDto2cust(CustomerDtoToCustomer dto2cust) {
        this.dto2cust = dto2cust;
    }

    @Autowired
    public void setCust2dto(CustomerToCustomerDto cust2dto) {
        this.cust2dto = cust2dto;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setAcc2dto(AccountToAccountDto acc2dto) {
        this.acc2dto = acc2dto;
    }

    @RequestMapping(
                method = RequestMethod.GET,
                value = "api/customer",
                produces = MediaType.APPLICATION_JSON_VALUE
        )
        public ResponseEntity<List<CustomerDto>> getCustomers() {

        List<CustomerDto> list = customerService.list().stream().
                map(customer -> cust2dto.convert(customer)).
                collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/customer/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CustomerDto> showCustomer(@PathVariable Integer id) {

        return new ResponseEntity<>(cust2dto.convert(customerService.get(id)),HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/customer/{id}/accounts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AccountDto>> showAccounts(@PathVariable Integer id) {

        List<AccountDto> list = customerService.get(id).getAccounts().stream().
                map(account -> acc2dto.convert(account)).
                collect(Collectors.toList());

        return new ResponseEntity<>(list,HttpStatus.OK);

    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "api/customer/{id}/accounts/{id2}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AccountDto> showOneAccount(@PathVariable Integer id, @PathVariable Integer id2) {


        return new ResponseEntity<>(acc2dto.convert(customerService.get(id).getAccounts().get(id2-1)),HttpStatus.OK);

    }










}
