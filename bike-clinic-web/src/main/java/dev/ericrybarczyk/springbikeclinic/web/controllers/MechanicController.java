package dev.ericrybarczyk.springbikeclinic.web.controllers;

import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;

@Controller
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    @RequestMapping({"/mechanics", "/mechanics/index", "/mechanics/index.html"})
    public String listMechanics(Model model) {
        model.addAttribute("mechanics", mechanicService.findAll());
        return "mechanics/index";
    }

    // super-simple way to get JSON data from a controller - JSON is default, XML is config option
    @GetMapping("/api/mechanics")
    public @ResponseBody Set<Mechanic> getMechanicsJson() {
        return mechanicService.findAll();
    }
}
