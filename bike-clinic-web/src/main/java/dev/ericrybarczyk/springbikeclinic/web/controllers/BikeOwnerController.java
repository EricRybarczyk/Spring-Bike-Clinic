package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
