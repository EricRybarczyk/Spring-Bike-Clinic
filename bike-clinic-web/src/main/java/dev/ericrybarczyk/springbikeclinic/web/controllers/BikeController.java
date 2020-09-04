package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.BikeService;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Collection;

@RequestMapping("/bikeOwners/{bikeOwnerId}/")
@Controller
public class BikeController {

    private final BikeOwnerService bikeOwnerService;
    private final BikeTypeService bikeTypeService;
    private final BikeService bikeService;

    public BikeController(BikeOwnerService bikeOwnerService, BikeTypeService bikeTypeService, BikeService bikeService) {
        this.bikeOwnerService = bikeOwnerService;
        this.bikeTypeService = bikeTypeService;
        this.bikeService = bikeService;
    }

    @ModelAttribute("bikeTypes")
    public Collection<BikeType> populateBikeTypes() {
        return bikeTypeService.findAll();
    }

    @ModelAttribute("bikeOwner")
    public BikeOwner findBikeOwner(@PathVariable Long bikeOwnerId) {
        return bikeOwnerService.findById(bikeOwnerId);
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("bikes/new")
    public String getNewBikeForm(BikeOwner bikeOwner, Model model) {
        Bike bike = new Bike();
        bikeOwner.getBikes().add(bike);
        bike.setOwner(bikeOwner);
        model.addAttribute("bike", bike);
        return "bikes/createOrUpdateBikeForm";
    }

    @GetMapping("bikes/{bikeId}/edit")
    public String getEditExistingBikeForm(@PathVariable Long bikeId, Model model) {
        model.addAttribute("bike", bikeService.findById(bikeId));
        return "bikes/createOrUpdateBikeForm";
    }

    @PostMapping("bikes/new")
    public String processNewBikeOwnerForm(@Valid BikeOwner bikeOwner, @Valid Bike bike, BindingResult bindingResult, Model model) {
        if (StringUtils.hasLength(bike.getDescription()) && bike.isNew() && bikeOwner.getBike(bike.getDescription(), true) != null) {
            bindingResult.rejectValue("description", "duplicate", "already exists");
        }
        bikeOwner.getBikes().add(bike);
        bike.setOwner(bikeOwner);
        if (bindingResult.hasErrors()) {
            model.addAttribute("bike", bike);
            return "bikes/createOrUpdateBikeForm";
        } else {
            bikeService.save(bike);
            return String.format("redirect:/bikeOwners/%s", bikeOwner.getId());
        }
    }

    @PostMapping("bikes/{bikeId}/edit")
    public String processEditExistingBikeForm(@ModelAttribute BikeOwner bikeOwner, @Valid @ModelAttribute Bike bike, @PathVariable Long bikeId, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bike.setOwner(bikeOwner);
            model.addAttribute("bike", bike);
            return "bikes/createOrUpdateBikeForm";
        } else {
            Bike foundBike = bikeService.findById(bikeId);
            foundBike.setOwner(bikeOwner);
            foundBike.setBikeType(bike.getBikeType());
            foundBike.setDescription(bike.getDescription());
            foundBike.setPurchaseDate(bike.getPurchaseDate());
            bikeService.save(foundBike);
            return String.format("redirect:/bikeOwners/%s", bikeOwner.getId());
        }
    }
}
