package com.example.webshopschool.controller;

import com.example.webshopschool.model.Membership;
import com.example.webshopschool.model.Product;
import com.example.webshopschool.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/product")
public class ProductController {
    public final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/add")
    public String addNewProduct(@RequestParam String productName,
                                @RequestParam String productNumber){
        return productService.addNewProduct(productName,productNumber);
    }

    @RequestMapping("/all")
    public String getAllProducts(Model model) {
        getProducts(model);
        return "allproducts";
    }

    private void getProducts(Model model) {
        Iterable<Product> allProducts = productService.getAllProducts();
        System.out.println(allProducts);
        model.addAttribute("allProducts", allProducts);
        model.addAttribute("productNumber", "Product #");
        model.addAttribute("productName", "Prodcut");
        model.addAttribute("productTitle", "All Products");
    }

    @RequestMapping("/getByProductId")
    public Product getProductById(@RequestParam Long productId){
        return productService.getProductById(productId);
    }

    //POST
    // Postman
    /*@PostMapping("/buy")
    public ResponseEntity<?> getOrdersByCustomerIdAndItemId(@RequestBody CustomerItemResponseDTO customerItemResponseDTO)
            throws CustomizedNotFoundException {
        Customer customer = customerRepository.findById(customerItemResponseDTO.getCustomerId()).orElseThrow(() ->
                new CustomizedNotFoundException(
                        "Customer with id: " + customerItemResponseDTO.getCustomerId() + " could not be found"));
        Item item = itemRepository.findById(customerItemResponseDTO.getItemId()).orElseThrow(() ->
                new CustomizedNotFoundException(
                        "Item with id: " + customerItemResponseDTO.getItemId() + " could not be found"));
        BuyOrder buyOrder = BuyOrder.builder().customer(customer).items(List.of(item)).build();
        buyOrderRepository.save(buyOrder);
        return new ResponseEntity<>(buyOrder, HttpStatus.CREATED);
    }*/
}
