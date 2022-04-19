package com.example.webshopschool.controller;

import com.example.webshopschool.model.Customer;
import com.example.webshopschool.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/add")
    public @ResponseBody
    String addNewCustomer(@RequestParam String name,
                          @RequestParam(required = false) String address){
        return customerService.addNewCustomer(name, address);
    }

    @GetMapping("/addByView")
    public String addCustomerByView(Model model) {
        Customer customer = new Customer();
        model.addAttribute("Customer", customer);
        return "addCustomer";
    }

    @GetMapping(path = "/getAllAfterAddByView")
    public String addNewCustomerByView(@RequestParam String name, @RequestParam String address,
                                   Model model) {
        customerService.addNewCustomer(name, address);
        return getAllCustomers(model);
    }

    @RequestMapping("/all")
    public String getAllCustomers(Model model){
        getCustomers(model);
        return "allcustomers";
    }

    private void getCustomers(Model model) {
        Iterable<Customer> allCustomers = customerService.getAllCustomers();
        System.out.println(allCustomers);
        model.addAttribute("allCustomers", allCustomers);
        model.addAttribute("nameTitle", "Name");
        model.addAttribute("addressTitle", "Address");
        model.addAttribute("customerTitle", "All Customers");
    }

    @RequestMapping("/allDelete")
    public String getAllDelete(Model model) {
        getCustomers(model);
        return "allCustomersWithDelete";
    }

    @RequestMapping(path = "/deleteById/{id}")
    public String deleteCustomer(@PathVariable Long id, Model model) {
        customerService.deleteCustomer(id);
        return getAllCustomers(model);
        /*return customerService.deleteCustomer(id);*/
    }

    @RequestMapping("/getCustomerById")
    public Customer getCustomerById(@RequestParam Long customerId){
        return customerService.getCustomerById(customerId);
    }

    @RequestMapping("/allEdit")
    public String getAllEditDelete(Model model) {
        getAllCustomers(model);
        return "allCustomersEdit";
    }

    @RequestMapping("/editByView/{id}")
    public String editByView(Model model, @PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        model.addAttribute("Customer", customer);
        return "editCustomer";
    }

    @PostMapping(path = "/update")
    public String editCap(@ModelAttribute Customer customer, Model model) {
        customerService.save(customer);
        return getAllCustomers(model);
    }
}
