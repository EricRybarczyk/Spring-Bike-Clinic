package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "bike_owners")
public class BikeOwner extends Person {

    /* This constructor with chained super calls isa required due to the inheritance hierarchy combined with using the @Builder annotation.
       Also, the @Singular annotation on the collection parameter so Lombok will treat that builder node as a collection, and generate 'adder' methods instead of a 'setter' method.
     */
    @Builder
    public BikeOwner(Long id, String firstName, String lastName, String address, String city, String telephone, String emailAddress, @Singular Set<Bike> bikes) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        this.emailAddress = emailAddress;
        if (bikes != null && bikes.size() > 0) {
            this.bikes = bikes;
        }
    }

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String emailAddress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Bike> bikes = new HashSet<>();

    public Bike getBike(String description) {
        return getBike(description, false);
    }

    public Bike getBike(final String description, boolean ignoreNew) {
        if (ignoreNew) {
            return getBikes().stream().filter(b -> !b.isNew() && description.equalsIgnoreCase(b.getDescription())).findFirst().orElse(null);
        } else {
            return getBikes().stream().filter(b -> description.equalsIgnoreCase(b.getDescription())).findFirst().orElse(null);
        }
    }
}
