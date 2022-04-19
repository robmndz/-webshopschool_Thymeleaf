package com.example.webshopschool.repository;

import com.example.webshopschool.model.Membership;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CrudRepository<Membership, Long> {
}
