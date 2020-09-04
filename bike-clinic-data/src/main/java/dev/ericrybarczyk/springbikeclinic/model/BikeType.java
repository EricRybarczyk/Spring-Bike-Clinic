package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bike_types")
public class BikeType extends BaseEntity {

    // This constructor with chained super calls is required due to the inheritance hierarchy combined with using the @Builder annotation.
    @Builder
    public BikeType(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

}
