package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bikes")
public class Bike extends BaseEntity {

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "bike_type_id")
    private BikeType bikeType;

    @ManyToOne
    @JoinColumn(name = "bike_owner_id")
    private BikeOwner owner;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bike")
    private Set<Visit> visits = new HashSet<>();

}
