package com.example.online_grocery.mapper;

import com.example.online_grocery.dto.CustomerDto;
import com.example.online_grocery.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getAddress(),
                customerDto.getPhone());

        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getPhone()

        );
        
        return customerDto;
    }

}

