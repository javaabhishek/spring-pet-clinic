package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }
    /*@RequestMapping({"","/","/index","/index.html"})
    public String index(Model model){
        Set<Owner> owners=ownerService.findAll();

        model.addAttribute("owners",owners);
        return "owners/index";
    }*/
    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(@RequestParam(defaultValue = "1") int page, Owner owner, BindingResult result,
                                  Model model) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }
        Set<Owner> ownersResults=ownerService.findAllOwnersByLastNameLike("%"+owner.getLastName()+"%");
        if (ownersResults.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        if (ownersResults.size() == 1) {
            // 1 owner found
            owner = ownersResults.iterator().next();
            return "redirect:/owners/" + owner.getId();
        }
        model.addAttribute("listOwners",ownersResults);
        return "/owners/ownersList";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");//ModelAndView is just different technique
        Owner owner = ownerService.findById(ownerId);
        mav.addObject(owner);
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        Owner owner=new Owner();
        model.addAttribute("owner",owner);
        return "owners/createOrUpdateOwnerForm";
    }
    @GetMapping("/{ownerId}/edit")
    public String initUpdateForm(@PathVariable Long ownerId, Model model){
        Owner owner=ownerService.findById(ownerId);
        model.addAttribute("owner",owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner,BindingResult result){
           if(result.hasErrors()){
               return "owners/createOrUpdateOwnerForm";
           }
           Owner savedOwner=ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }
    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(Owner owner,BindingResult result,@PathVariable Long ownerId){
        if(result.hasErrors()){
            return "owners/createOrUpdateOwnerForm";
        }
        owner.setId(ownerId);
        Owner savedOwner=ownerService.save(owner);
        return "redirect:/owners/"+savedOwner.getId();
    }
}
