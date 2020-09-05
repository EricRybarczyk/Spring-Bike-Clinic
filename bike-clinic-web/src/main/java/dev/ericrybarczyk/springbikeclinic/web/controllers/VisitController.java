package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.Visit;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import dev.ericrybarczyk.springbikeclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;

@RequestMapping("/bikeOwners/{bikeOwnerId}/bikes/{bikeId}/visits")
@Controller
public class VisitController {

    private final VisitService visitService;
    private final BikeService bikeService;

    public VisitController(VisitService visitService, BikeService bikeService) {
        this.visitService = visitService;
        this.bikeService = bikeService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    // this is processed prior to any get/post method
    @ModelAttribute("visit")
    public Visit loadBikeWithVisit(@PathVariable Long bikeId, Model model) {
        Bike bike = bikeService.findById(bikeId);
        model.addAttribute("bike", bike);
        Visit visit = new Visit();
        bike.getVisits().add(visit);
        visit.setBike(bike);
        return visit;
    }

    @GetMapping("/new")
    public String getNewVisitForm() { // @PathVariable Long bikeId, Model model
        return "bikes/createOrUpdateVisitForm";
    }

    // here, Spring will update the Visit from @ModelAttribute with the values in the Visit method parameter
    @PostMapping("/new")
    public String processNewVisitForm(@Valid Visit visit, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "bikes/createOrUpdateVisitForm";
        }
        visitService.save(visit);
        return "redirect:/bikeOwners/{bikeOwnerId}";
    }
}
