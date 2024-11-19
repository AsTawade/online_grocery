package com.example.online_grocery.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.online_grocery.dto.CustomerDto;
import com.example.online_grocery.entity.Customer;
import com.example.online_grocery.mapper.CustomerMapper;
import com.example.online_grocery.repository.CustomerRepository;
import com.example.online_grocery.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(savedCustomer);

    }

    @Override
    public CustomerDto getCustomerById(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Customer does not exists"));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(CustomerMapper::mapToCustomerDto)
                .toList();
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Customer does not exists"));
        
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Long id) {
        Customer existingCustomer = customerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Customer does not exists"));

                existingCustomer.setName(customerDto.getName());
                existingCustomer.setEmail(customerDto.getEmail());
                existingCustomer.setAddress(customerDto.getAddress());
                existingCustomer.setPhone(customerDto.getPhone());
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);        

        return CustomerMapper.mapToCustomerDto(updatedCustomer);
    }

}
