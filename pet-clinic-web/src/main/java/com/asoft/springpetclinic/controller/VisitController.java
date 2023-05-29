package com.asoft.springpetclinic.controller;

import com.asoft.springpetclinic.model.Owner;
import com.asoft.springpetclinic.model.Pet;
import com.asoft.springpetclinic.model.Visit;
import com.asoft.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class VisitController {

	private final OwnerService ownerService;

	public VisitController(OwnerService ownerService) {
		this.ownerService = ownerService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	/**
	 * Called before each and every @RequestMapping annotated method. 2 goals: - Make sure
	 * we always have fresh data - Since we do not use the session scope, make sure that
	 * Pet object always has an id (Even though id is not part of the form fields)
	 * @param petId
	 * @return Pet
	 */
	@ModelAttribute("visit")
	public Visit loadPetWithVisit(@PathVariable("ownerId") Long ownerId, @PathVariable("petId") Long petId,
								  Model model) {
		Owner owner = this.ownerService.findById(ownerId);

		Pet pet = owner.getPets().stream().filter(pt->pt.getId().equals(petId)).findFirst().orElse(null);
		model.addAttribute("pet", pet);
		model.addAttribute("owner", owner);

		Visit visit = new Visit();
		pet.getVisits().add(visit);
		return visit;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is
	// called
	@GetMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is
	// called
	@PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
	public String processNewVisitForm(@ModelAttribute Owner owner, @PathVariable Long petId, Visit visit,
			BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}

		Pet pet=owner.getPets().stream().filter(pt->pt.getId().equals(petId)).findFirst().orElse(null);
		pet.getVisits().add(visit);
		visit.setPet(pet);
		ownerService.save(owner);
		return "redirect:/owners/{ownerId}";
	}

}