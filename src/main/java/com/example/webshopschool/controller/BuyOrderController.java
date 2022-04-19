package com.example.webshopschool.controller;

import com.example.webshopschool.model.BuyOrder;
import com.example.webshopschool.model.Product;
import com.example.webshopschool.services.BuyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class BuyOrderController {

    private final BuyOrderService orderService;

    @Autowired
    public BuyOrderController(BuyOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/add")
    public String addNewOrder(@RequestParam String orderNumber,
                              @RequestParam Long customerId){
        return orderService.addNewOrder(orderNumber, customerId);
    }

    @RequestMapping("/all")
    public String getAllOrders(Model model){
        getOrders(model);
        return "allorders";
    }


    private void getOrders(Model model) {
        Iterable<BuyOrder> allOrders = orderService.getAllOrders();
        System.out.println(allOrders);
        model.addAttribute("allOrders", allOrders);
        model.addAttribute("orderNumber", "Order #");
        model.addAttribute("customertName", "Customer Name");
        model.addAttribute("orderTitle", "All Orders");
    }

    @RequestMapping("/getOrderById")
    public BuyOrder getOrderById(@RequestParam Long orderId){
        return orderService.getOrderById(orderId);
    }

    @RequestMapping("/addOrderWithProductId")
    public String addNewOrderWithOrderItem(@RequestParam String orderNumber,
                                           @RequestParam Long customerId,
                                           @RequestParam Long productId){
        return orderService.addNewOrderWithOrderItem(orderNumber, customerId, productId);
    }

    @RequestMapping("/addNewOrderCustomerAndProduct")
    public String addNewOrderCustomerAndProduct(@RequestParam String orderNumber, @RequestParam String customerName,
                                                @RequestParam String customerAddress, @RequestParam String productName,
                                                @RequestParam String productNumber){
        return orderService.addNewOrderCustomerAndProduct(orderNumber, customerName,
                customerAddress, productName, productNumber);
    }
}
