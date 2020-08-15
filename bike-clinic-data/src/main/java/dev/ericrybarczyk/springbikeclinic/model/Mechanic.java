package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mechanics")
public class Mechanic extends Person {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mechanic_specialty", joinColumns = @JoinColumn(name = "mechanic_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

}
