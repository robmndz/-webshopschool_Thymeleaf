package com.example.webshopschool.bootstrap;

import com.example.webshopschool.model.BuyOrder;
import com.example.webshopschool.model.Customer;
import com.example.webshopschool.model.Membership;
import com.example.webshopschool.model.Product;
import com.example.webshopschool.repository.BuyOrderRepository;
import com.example.webshopschool.repository.CustomerRepository;
import com.example.webshopschool.repository.MembershipRepository;
import com.example.webshopschool.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;
import java.util.List;


@Lazy
@Configuration
public class DataInitializer/* implements CommandLineRunner */{
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final MembershipRepository memberRepository;
    private final CustomerRepository customerRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final ProductRepository productRepository;

    public DataInitializer(MembershipRepository memberRepository,
                           CustomerRepository customerRepository,
                           BuyOrderRepository buyOrderRepository,
                           ProductRepository productRepository) {
        this.memberRepository = memberRepository;
        this.customerRepository = customerRepository;
        this.buyOrderRepository = buyOrderRepository;
        this.productRepository = productRepository;
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            memberRepository.deleteAll();
            buyOrderRepository.deleteAll();
            customerRepository.deleteAll();

            Customer customer1 = new Customer("Ali Moteirek", "Jönköping");
            customerRepository.save(customer1);

            Membership member = new Membership("1",
                    new Customer("Yassir Al Dahwi", "Malmö"));
            Membership member1 = new Membership("2",
                    new Customer("Hassan Jawad", "Beirut"));
            memberRepository.save(member);
            memberRepository.save(member1);

            BuyOrder buyOrder = new BuyOrder("10",
                    new Customer("Roberto Mendez", "Stockholm"),
                    List.of(new Product("Macbook Pro", "1"),
                            new Product("Macbook Air", "2")));
            BuyOrder buyOrder1 = new BuyOrder("11",
                    new Customer("James Carter", "New Jerssy"),
                    List.of(new Product("Airpods", "3")));
            buyOrderRepository.save(buyOrder);
            buyOrderRepository.save(buyOrder1);

        };
    }


}
