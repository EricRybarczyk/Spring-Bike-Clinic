package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/bikeOwners")
@Controller
public class BikeOwnerController {

    private final BikeOwnerService bikeOwnerService;

    public BikeOwnerController(BikeOwnerService bikeOwnerService) {
        this.bikeOwnerService = bikeOwnerService;
    }

    @RequestMapping({"", "/", "/index","/index.html"})
    public String listBikeOwners(Model model) {
        model.addAttribute("owners", bikeOwnerService.findAll());
        return "bikeOwners/index";
    }

    @RequestMapping("/find")
    public String findBikeOwners() {
        return "notimplemented";
    }

    @GetMapping("/{bikeOwnerId}")
    public ModelAndView showBikeOwner(@PathVariable("bikeOwnerId") Long bikeOwnerId) {
        ModelAndView modelAndView = new ModelAndView("bikeOwners/bikeOwnerDetails");
        modelAndView.addObject(bikeOwnerService.findById(bikeOwnerId));
        return modelAndView;
    }

}
