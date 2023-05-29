package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.model.PetType;
import com.asoft.springpetclinic.services.OwnerService;
import com.asoft.springpetclinic.services.PetService;
import com.asoft.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;


    public PetController(OwnerService ownerService, PetService petService,
                         PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }
    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("ownerId") Long ownerId,
                       @PathVariable(name = "petId", required = false) Long petId) {
        Pet pet=null;
        if(petId == null)
            pet=new Pet();
        else
            pet=ownerService.findById(ownerId).getPets().stream().filter(pt->pt.getId().equals(petId)).findFirst().orElse(null);
        return pet;
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        //dataBinder.setValidator(new PetValidator());
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner,Model model){
        Pet pet=new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult result, Model model) {
        if (StringUtils.hasLength(pet.getPetName()) && pet.isNew() && owner.getPets().stream()
                .filter(ownrPt->ownrPt.getPetName().equals(pet.getPetName())).findFirst().orElse(null) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }

        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        ownerService.save(owner);
        return "redirect:/owners/{ownerId}";
    }
    @GetMapping("/pets/{petId}/edit")
    public String initUpdateForm(Owner owner, @PathVariable("petId") Long petId, Model model) {
        Pet pet = owner.getPets().stream()
                .filter(ownrPt->ownrPt.getId().equals(petId)).findFirst().get();
        model.addAttribute("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processUpdateForm(Pet pet, BindingResult result, Owner owner, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }

        owner.getPets().add(pet);
        ownerService.save(owner);
        return "redirect:/owners/{ownerId}";
    }
}
