package dev.ericrybarczyk.springbikeclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/bikeOwners")
@Controller
public class BikeOwnerController {

    @RequestMapping({"", "/", "/index","/index.html"})
    public String listBikeOwners() {
        return "bikeOwners/index";
    }
}
