package com.example.webshopschool.repository;

import com.example.webshopschool.model.BuyOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyOrderRepository extends CrudRepository<BuyOrder, Long> {
}
