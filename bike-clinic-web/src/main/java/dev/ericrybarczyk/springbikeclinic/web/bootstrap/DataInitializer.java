package dev.ericrybarczyk.springbikeclinic.web.bootstrap;

import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BikeOwnerService bikeOwnerService;
    private final MechanicService mechanicService;

    public DataInitializer(BikeOwnerService bikeOwnerService, MechanicService mechanicService) {
        this.bikeOwnerService = bikeOwnerService;
        this.mechanicService = mechanicService;
    }

    @Override
    public void run(String... args) throws Exception {
        BikeOwner owner1 = new BikeOwner();
        owner1.setId(1L);
        owner1.setFirstName("John");
        owner1.setLastName("Smith");
        bikeOwnerService.save(owner1);

        BikeOwner owner2 = new BikeOwner();
        owner2.setId(2L);
        owner2.setFirstName("Sarah");
        owner2.setLastName("Jones");
        bikeOwnerService.save(owner2);

        System.out.println("Loaded BikeOwner bootstrap data.");

        Mechanic mechanic1 = new Mechanic();
        mechanic1.setId(1L);
        mechanic1.setFirstName("Kyle");
        mechanic1.setLastName("Fisher");
        mechanicService.save(mechanic1);

        Mechanic mechanic2 = new Mechanic();
        mechanic2.setId(2L);
        mechanic2.setFirstName("Terri");
        mechanic2.setLastName("Manning");
        mechanicService.save(mechanic2);

        System.out.println("Loaded Mechanic bootstrap data.");
    }
}
