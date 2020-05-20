package dev.ericrybarczyk.springbikeclinic.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MechanicController {

    @RequestMapping({"/mechanics", "/mechanics/index", "/mechanics/index.html"})
    public String listMechanics() {
        return "mechanics/index";
    }
}
