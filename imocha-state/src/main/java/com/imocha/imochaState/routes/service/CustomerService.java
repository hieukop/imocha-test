package com.imocha.imochaState.routes.service;

import com.imocha.imochaState.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAll();
    void save(Customer customer);
}
