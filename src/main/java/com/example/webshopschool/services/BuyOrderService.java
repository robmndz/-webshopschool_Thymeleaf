package com.example.webshopschool.services;

import com.example.webshopschool.model.BuyOrder;
import com.example.webshopschool.model.Customer;
import com.example.webshopschool.model.Product;
import com.example.webshopschool.repository.BuyOrderRepository;
import com.example.webshopschool.repository.CustomerRepository;
import com.example.webshopschool.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;


@Service
public class BuyOrderService {

    private final BuyOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public BuyOrderService(BuyOrderRepository orderRepository,
                           CustomerRepository customerRepository,
                           ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public String addNewOrder(String orderNumber, Long customerId) {
        BuyOrder order = new BuyOrder(orderNumber);
        Optional<Customer> orderOptional = customerRepository.findById(customerId);
        orderOptional.ifPresent(order::setCustomer);
        orderRepository.save(order);
        return "A new order with order number: " + orderNumber + " is saved";
    }

    public Iterable<BuyOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public BuyOrder getOrderById(Long orderId) {
        if (orderRepository.findById(orderId).isPresent())
            return orderRepository.findById(orderId).get();
        else return null;
    }

    public String addNewOrderWithOrderItem(String orderNumber, Long customerId, Long productId) {
        BuyOrder order = new BuyOrder(orderNumber);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Optional<Product> productOptional = productRepository.findById(productId);
        Consumer<Customer> setCustomer = order::setCustomer;
        customerOptional.ifPresent(setCustomer);
        /*productOptional.ifPresent(order::setProductList);*/
        /*if(productOptional.isPresent()){
            order.setOrderDetails(List.of(productOptional.get()));
        }*/
        productOptional.ifPresent(product -> order.setProducts(List.of(product)));

        orderRepository.save(order);
        return "A new order with order number: " + orderNumber + " is saved";
    }

    public String addNewOrderCustomerAndProduct(String orderNumber, String customerName,
                                                String customerAddress, String productName, String productNumber){
        orderRepository.save(new BuyOrder(orderNumber,
                new Customer(customerName, customerAddress), List.of(new Product(productName, productNumber))));
        return "A new order with number: " + orderNumber + " and a new customer and product are eventually " +
                "added with the names " + customerName + " and " + productName + " respectively";
    }
}
