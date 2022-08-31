package com.imocha.imochaState.controllers;

import com.imocha.imochaState.dtos.CustomerAdd;
import com.imocha.imochaState.dtos.CustomerView;
import com.imocha.imochaState.entity.Customer;
import com.imocha.imochaState.routes.serviceImpl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl service;

    @GetMapping("")
    public ModelAndView showCustomerList() {
        ModelAndView modelAndView = new ModelAndView("list");
        List<CustomerView> customerViews = service.getAll().stream().map(Customer::parseView).collect(Collectors.toList());
        modelAndView.addObject("customerlist", customerViews);
        return modelAndView;
    }

    @GetMapping("/addCustomer")
    public ModelAndView showCustomerAdd() {
        ModelAndView modelAndView = new ModelAndView("add");
        modelAndView.addObject("customerAdd", new CustomerAdd());
        return modelAndView;
    }

    @PostMapping("/addCustomer")
    public RedirectView addCustomer(@ModelAttribute("customerAdd") CustomerAdd customerAdds) {
        try {
            Customer newCustomer = new Customer().parseAdd(customerAdds);
            service.save(newCustomer);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new RedirectView("/customer");
    }

}
