package com.example.webshopschool.controller;

import com.example.webshopschool.model.Customer;
import com.example.webshopschool.model.Membership;
import com.example.webshopschool.services.MembershipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/membership")
public class MembershipController {

    private static final Logger log = LoggerFactory.getLogger(MembershipController.class);

    private final MembershipService memberService;

    @Autowired
    public MembershipController(MembershipService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/add")
    public String addNewMember(@RequestParam String memberNumber,
                               @RequestParam Long customerId){
        log.info("Member # " + memberNumber + " was added to database");
        return memberService.addNewMember(memberNumber, customerId);
    }

    @RequestMapping("/all")
    public String getAllMembers(Model model) {
        getMembers(model);
        return "allmembers";
    }

    private void getMembers(Model model) {
        Iterable<Membership> allMembers = memberService.getAllMembers();
        System.out.println(allMembers);
        model.addAttribute("allMembers", allMembers);
        model.addAttribute("mbershipNumber", "Membership #");
        model.addAttribute("nameTitle", "Name");
        model.addAttribute("addressTitle", "Address");
        model.addAttribute("memberTitle", "All Members");
    }

    @RequestMapping("/getMembershipById")
    public Membership getMemberById(@RequestParam Long memberId){
        return memberService.getMemberById(memberId);
    }

    @RequestMapping("/addNewMembershipAndCustomer")
    public String addNewMemberAndCustomer(@RequestParam String memberNumber,
                                          @RequestParam String customerName,
                                          @RequestParam String customerAddress){
        return memberService.addNewMemberAndCustomer(memberNumber, customerName, customerAddress);
    }
}
