package dev.ericrybarczyk.springbikeclinic.web.bootstrap;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BikeOwnerService bikeOwnerService;
    private final MechanicService mechanicService;
    private final BikeTypeService bikeTypeService;

    public DataInitializer(BikeOwnerService bikeOwnerService, MechanicService mechanicService, BikeTypeService bikeTypeService) {
        this.bikeOwnerService = bikeOwnerService;
        this.mechanicService = mechanicService;
        this.bikeTypeService = bikeTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        BikeType mountain = new BikeType();
        mountain.setName("Mountain");
        mountain = bikeTypeService.save(mountain);

        BikeType gravel = new BikeType();
        gravel.setName("Gravel");
        gravel = bikeTypeService.save(gravel);

        BikeType road = new BikeType();
        road.setName("Road");
        road = bikeTypeService.save(road);


        BikeOwner owner1 = new BikeOwner();
        owner1.setFirstName("John");
        owner1.setLastName("Smith");
        bikeOwnerService.save(owner1);

        BikeOwner owner2 = new BikeOwner();
        owner2.setFirstName("Sarah");
        owner2.setLastName("Jones");
        bikeOwnerService.save(owner2);

        System.out.println("Loaded BikeOwner bootstrap data.");

        Mechanic mechanic1 = new Mechanic();
        mechanic1.setFirstName("Kyle");
        mechanic1.setLastName("Fisher");
        mechanicService.save(mechanic1);

        Mechanic mechanic2 = new Mechanic();
        mechanic2.setFirstName("Terri");
        mechanic2.setLastName("Manning");
        mechanicService.save(mechanic2);

        System.out.println("Loaded Mechanic bootstrap data.");
    }
}
