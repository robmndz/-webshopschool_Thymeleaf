package com.example.webshopschool.services;

import com.example.webshopschool.model.Customer;
import com.example.webshopschool.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String addNewCustomer(String name, String address) {
        customerRepository.save(new Customer(name, address));
        return name + " is saved";
    }

    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /*public String deleteCustomer(Long id) {
        customerRepository.deleteById(id);
        return "Customer with id: " + id + " was deleted";
    }*/

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }


    public Customer getCustomerById(Long customerId) {
        if (customerRepository.findById(customerId).isPresent())
            return customerRepository.findById(customerId).get();
        else return null;
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
