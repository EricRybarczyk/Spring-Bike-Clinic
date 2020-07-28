package dev.ericrybarczyk.springbikeclinic.web.bootstrap;

import dev.ericrybarczyk.springbikeclinic.model.Bike;
import dev.ericrybarczyk.springbikeclinic.model.BikeOwner;
import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.model.Mechanic;
import dev.ericrybarczyk.springbikeclinic.services.BikeOwnerService;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import dev.ericrybarczyk.springbikeclinic.services.MechanicService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

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
        owner1.setAddress("123 Send It Dr.");
        owner1.setCity("Sandpoint");
        owner1.setTelephone("2085551212");
        owner1.setEmailAddress("john@domain.com");

        Bike johnBike1 = new Bike();
        johnBike1.setOwner(owner1);
        johnBike1.setBikeType(mountain);
        johnBike1.setPurchaseDate(LocalDate.of(2017, 1, 26));
        owner1.getBikes().add(johnBike1);
        bikeOwnerService.save(owner1);

        BikeOwner owner2 = new BikeOwner();
        owner2.setFirstName("Sarah");
        owner2.setLastName("Jones");
        owner2.setAddress("456 Bunny Hop Dr.");
        owner2.setCity("Crested Butte");
        owner2.setTelephone("9705551212");
        owner2.setEmailAddress("sarah@domain.com");

        Bike saraBike1 = new Bike();
        saraBike1.setOwner(owner1);
        saraBike1.setBikeType(gravel);
        saraBike1.setPurchaseDate(LocalDate.of(2018, 4, 15));
        owner2.getBikes().add(saraBike1);
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
