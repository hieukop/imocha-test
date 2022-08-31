package com.imocha.imochaState.routes.serviceImpl;

import com.imocha.imochaState.entity.Customer;
import com.imocha.imochaState.routes.repositories.CustomerDAO;
import com.imocha.imochaState.routes.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public List<Customer> getAll() {
        return customerDAO.findAll();
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }
}
