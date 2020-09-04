package dev.ericrybarczyk.springbikeclinic.web.bootstrap;

import dev.ericrybarczyk.springbikeclinic.model.*;
import dev.ericrybarczyk.springbikeclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BikeOwnerService bikeOwnerService;
    private final MechanicService mechanicService;
    private final BikeTypeService bikeTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataInitializer(BikeOwnerService bikeOwnerService, MechanicService mechanicService, BikeTypeService bikeTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.bikeOwnerService = bikeOwnerService;
        this.mechanicService = mechanicService;
        this.bikeTypeService = bikeTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bikeTypeService.findAll().size() == 0) { // simple check to prevent data duplication
            loadInitialData();
        }
    }

    private void loadInitialData() {

        // BikeType -----------------------------------------------------------

        BikeType mountain = new BikeType();
        mountain.setName("Mountain");
        mountain = bikeTypeService.save(mountain);

        BikeType gravel = new BikeType();
        gravel.setName("Gravel");
        gravel = bikeTypeService.save(gravel);

        BikeType road = new BikeType();
        road.setName("Road");
        road = bikeTypeService.save(road);

        System.out.println("Loaded BikeType bootstrap data.");

        // Specialty ----------------------------------------------------------

        Specialty suspensionSpecialty = new Specialty();
        suspensionSpecialty.setDescription("Suspension");
        suspensionSpecialty = specialtyService.save(suspensionSpecialty);

        Specialty driveTrainSpecialty = new Specialty();
        driveTrainSpecialty.setDescription("Drive Train");
        driveTrainSpecialty = specialtyService.save(driveTrainSpecialty);

        Specialty wheelTruingSpecialty = new Specialty();
        wheelTruingSpecialty.setDescription("Wheel Truing");
        wheelTruingSpecialty = specialtyService.save(wheelTruingSpecialty);

        System.out.println("Loaded Specialty bootstrap data.");

        // BikeOwner ----------------------------------------------------------

        BikeOwner owner1 = new BikeOwner();
        owner1.setFirstName("John");
        owner1.setLastName("Smith");
        owner1.setAddress("123 Send It Dr.");
        owner1.setCity("Sandpoint");
        owner1.setTelephone("2085551212");
        owner1.setEmailAddress("john@domain.com");

        Bike johnBike1 = new Bike();
        johnBike1.setOwner(owner1);
        johnBike1.setDescription("Hardy Hardtail");
        johnBike1.setBikeType(mountain);
        johnBike1.setPurchaseDate(LocalDate.of(2017, 1, 26));
        owner1.getBikes().add(johnBike1);
        bikeOwnerService.save(owner1);

        Visit johnVisit = new Visit();
        johnVisit.setBike(johnBike1);
        johnVisit.setDate(LocalDate.of(2020, 07, 29));
        johnVisit.setDescription("True the front wheel");
        visitService.save(johnVisit);

        BikeOwner owner2 = new BikeOwner();
        owner2.setFirstName("Sarah");
        owner2.setLastName("Jones");
        owner2.setAddress("456 Bunny Hop Dr.");
        owner2.setCity("Crested Butte");
        owner2.setTelephone("9705551212");
        owner2.setEmailAddress("sarah@domain.com");

        Bike saraBike1 = new Bike();
        saraBike1.setOwner(owner2);
        saraBike1.setDescription("Dirt Rocket");
        saraBike1.setBikeType(gravel);
        saraBike1.setPurchaseDate(LocalDate.of(2018, 4, 15));
        owner2.getBikes().add(saraBike1);
        bikeOwnerService.save(owner2);

        Visit saraVisit = new Visit();
        saraVisit.setBike(saraBike1);
        saraVisit.setDate(LocalDate.of(2020, 8, 3));
        saraVisit.setDescription("Brake bleed");
        visitService.save(saraVisit);

        // Try out the Builder from Lombok
        BikeOwner owner3 = BikeOwner.builder()
                .firstName("Michael")
                .lastName("Thomas")
                .address("5150 Rocking Lane")
                .city("Pasadena")
                .telephone("8889845150")
                .emailAddress("michael@domain.com")
                .build();
        Bike mikeBike1 = Bike.builder()
                .bikeType(road)
                .description("Crazy Speedy")
                .purchaseDate(LocalDate.of(2017, 7, 4))
                .owner(owner3)
                .build();
        owner3.getBikes().add(mikeBike1);
        Visit mikeBikeVisit = Visit.builder()
                .bike(mikeBike1)
                .date(LocalDate.of(2020, 8, 11))
                .description("New tires")
                .build();
        bikeOwnerService.save(owner3);
        visitService.save(mikeBikeVisit);

        System.out.println("Loaded BikeOwner bootstrap data.");

        // Mechanic -----------------------------------------------------------

        Mechanic mechanic1 = new Mechanic();
        mechanic1.setFirstName("Kyle");
        mechanic1.setLastName("Fisher");
        mechanic1.getSpecialties().add(driveTrainSpecialty);
        mechanicService.save(mechanic1);

        Mechanic mechanic2 = new Mechanic();
        mechanic2.setFirstName("Terri");
        mechanic2.setLastName("Manning");
        mechanic2.getSpecialties().add(wheelTruingSpecialty);
        mechanic2.getSpecialties().add(suspensionSpecialty);
        mechanicService.save(mechanic2);

        System.out.println("Loaded Mechanic bootstrap data.");
    }
}
