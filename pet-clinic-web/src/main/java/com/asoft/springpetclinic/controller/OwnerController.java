package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String index(Model model){
        Set<Owner> owners=ownerService.findAll();

        model.addAttribute("owners",owners);
        return "owners/index";
    }
    @RequestMapping("/find")
    public String findOwners(){
        return "notImplemented";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");//ModelAndView is just different technique
        Owner owner = ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }
}
