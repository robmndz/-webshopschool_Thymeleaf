package com.example.webshopschool.services;

import com.example.webshopschool.model.Customer;
import com.example.webshopschool.model.Membership;
import com.example.webshopschool.repository.CustomerRepository;
import com.example.webshopschool.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembershipService {

    private final CustomerRepository customerRepository;
    private final MembershipRepository memberRepository;

    @Autowired
    public MembershipService(CustomerRepository customerRepository, MembershipRepository memberRepository) {
        this.customerRepository = customerRepository;
        this.memberRepository = memberRepository;
    }

    public String addNewMember (String membershipNumber, Long customerId){
        Membership member = new Membership(membershipNumber);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        /*if(customerOptional.isPresent()){
            member.setCustomer(customerOptional.get());
        }*/
        customerOptional.ifPresent(member::setCustomer);
        memberRepository.save(member);
        return "A new member with membership number: " + membershipNumber + " is saved";
    }

    public Iterable<Membership> getAllMembers(){
        return memberRepository.findAll();
    }

    public Membership getMemberById (Long memberId){
        if(memberRepository.findById(memberId).isPresent())
            return memberRepository.findById(memberId).get();
        else return null;
    }

    public String addNewMemberAndCustomer(String memberNumber, String customerName, String customerAddress){
        memberRepository.save(new Membership(memberNumber, new Customer(customerName, customerAddress)));
        return "A new member with number: " + memberNumber + " and a new customer is eventually added with the name " + customerName;
    }
}
