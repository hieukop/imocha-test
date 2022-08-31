package com.imocha.imochaState.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imocha.imochaState.dtos.CustomerAdd;
import com.imocha.imochaState.dtos.CustomerView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

@Entity
@Table(name = "customer", schema = "imocha_state")
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    @JsonProperty(value = "name")
    private String name;

    @Basic
    @Column(name = "birth_date", nullable = false)
    @JsonProperty(value = "birth_date")
    private Date birthDate;

    @Basic
    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    @JsonProperty(value = "address")
    private String address;

    @Basic
    @Column(name = "post_code", nullable = false, length = 50)
    @JsonProperty(value = "post_code")
    private String postCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    @JsonProperty(value = "state")
    private State state;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public CustomerView parseView() {
        CustomerView customerView = new CustomerView();
        customerView.setName(this.name);
        customerView.setAge(Period.between(this.birthDate.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now()).getYears());
        customerView.setAddress(this.address);
        customerView.setPostcode(this.postCode);
        customerView.setState(this.state);
        return customerView;
    }

    public Customer parseAdd(CustomerAdd customerAdd) throws ParseException {
        this.setName(customerAdd.getName());
        this.setAddress(customerAdd.getAddress());
        this.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(customerAdd.getDate()));
        this.setPostCode(customerAdd.getPostcode());
        switch (customerAdd.getState()) {
            case "1":
                this.setState(State.ONE);
                break;
            case "2":
                this.setState(State.TWO);
                break;
            case "3":
                this.setState(State.THREE);
                break;

        }
        return this;
    }
}
