package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/bikeOwners")
@Controller
public class BikeOwnerController {

    private final BikeOwnerService bikeOwnerService;

    public BikeOwnerController(BikeOwnerService bikeOwnerService) {
        this.bikeOwnerService = bikeOwnerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/find")
    public String findBikeOwners(Model model) {
        model.addAttribute("bikeOwner", BikeOwner.builder().build());
        return "bikeOwners/findOwners";
    }

    @GetMapping({"", "/"})
    public String processFindOwnerForm(BikeOwner bikeOwner, BindingResult result, Model model) {
        List<BikeOwner> searchResult;
        if (bikeOwner.getLastName() == null || bikeOwner.getLastName().isBlank()) { // parameterless GET should return all owners
            searchResult = new ArrayList<>(bikeOwnerService.findAll());
        } else {  // find by last name
            searchResult = bikeOwnerService.findAllByLastNameLikeIgnoreCase(bikeOwner.getLastName() + "%");
        }
        switch (searchResult.size()) {
            case 0:
                result.rejectValue("lastName", "notFound", "not found");
                return "bikeOwners/findOwners";
            case 1:
                bikeOwner = searchResult.get(0);
                return String.format("redirect:/bikeOwners/%s", bikeOwner.getId());
            default:
                // multiple results
                model.addAttribute("bikeOwners", searchResult);
                return "bikeOwners/ownersList";
        }
    }

    @GetMapping("/{bikeOwnerId}")
    public ModelAndView showBikeOwner(@PathVariable Long bikeOwnerId) {
        ModelAndView modelAndView = new ModelAndView("bikeOwners/bikeOwnerDetails");
        modelAndView.addObject(bikeOwnerService.findById(bikeOwnerId));
        return modelAndView;
    }

    @GetMapping("/new")
    public String getNewBikeOwnerForm(Model model) {
        model.addAttribute("bikeOwner", BikeOwner.builder().build());
        return "bikeOwners/createOrUpdateBikeOwnerForm";
    }

    @GetMapping("/{bikeOwnerId}/edit")
    public String getEditExistingBikeOwnerForm(@PathVariable Long bikeOwnerId, Model model) {
        BikeOwner bikeOwner = bikeOwnerService.findById(bikeOwnerId);
        model.addAttribute("bikeOwner", bikeOwner);
        return "bikeOwners/createOrUpdateBikeOwnerForm";
    }

    @PostMapping("/new")
    public String processNewBikeOwnerForm(@Valid BikeOwner bikeOwner, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "bikeOwners/createOrUpdateBikeOwnerForm";
        }
        BikeOwner savedBikeOwner = bikeOwnerService.save(bikeOwner);
        model.addAttribute("bikeOwner", savedBikeOwner);
        return String.format("redirect:/bikeOwners/%s", savedBikeOwner.getId());
    }

    @PostMapping("/{bikeOwnerId}/edit")
    public String processUpdateExistingBikeOwnerForm(@Valid BikeOwner bikeOwner, BindingResult bindingResult, @PathVariable Long bikeOwnerId) {
        if (bindingResult.hasErrors()) {
            return "bikeOwners/createOrUpdateBikeOwnerForm";
        }
        bikeOwner.setId(bikeOwnerId);
        BikeOwner savedBikeOwner = bikeOwnerService.save(bikeOwner);
        return String.format("redirect:/bikeOwners/%s", savedBikeOwner.getId());
    }
}
