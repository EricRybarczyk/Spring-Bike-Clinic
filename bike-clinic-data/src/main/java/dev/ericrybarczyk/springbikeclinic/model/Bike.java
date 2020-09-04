package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
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

    // This constructor with chained super calls is required due to the inheritance hierarchy combined with using the @Builder annotation.
    @Builder
    public Bike(Long id, String description, BikeType bikeType, BikeOwner owner, LocalDate purchaseDate, Set<Visit> visits) {
        super(id);
        this.description = description;
        this.bikeType = bikeType;
        this.owner = owner;
        this.purchaseDate = purchaseDate;
        if (visits != null && visits.size() > 0) {
            this.visits = visits;
        }
    }

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "bike_type_id")
    private BikeType bikeType;

    @ManyToOne
    @JoinColumn(name = "bike_owner_id")
    private BikeOwner owner;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bike")
    private Set<Visit> visits = new HashSet<>();

}
