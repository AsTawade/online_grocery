package com.example.online_grocery.service;

import java.util.List;

import com.example.online_grocery.dto.CustomerDto;

public interface CustomerService {
    
    CustomerDto createCustomer(CustomerDto customerDto);

    CustomerDto getCustomerById(Long id);

    List<CustomerDto> getAllCustomers();

    void deleteCustomer(Long id);

    CustomerDto updateCustomer(CustomerDto customerDto, Long id);
}
