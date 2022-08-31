package com.imocha.imochaState.routes.repositories;

import com.imocha.imochaState.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDAO extends PagingAndSortingRepository<Customer, Integer> {

    @Query(value = "SELECT * FROM customer", nativeQuery = true)
    List<Customer> findAll();
}
