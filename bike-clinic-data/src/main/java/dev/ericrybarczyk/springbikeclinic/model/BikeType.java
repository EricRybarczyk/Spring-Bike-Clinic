package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bike_types")
public class BikeType extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }

}
