package dev.ericrybarczyk.springbikeclinic.model;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mechanics")
public class Mechanic extends Person {

    /* This constructor with chained super calls isa required due to the inheritance hierarchy combined with using the @Builder annotation.
       Also, the @Singular annotation on the collection parameter so Lombok will treat that builder node as a collection, and generate 'adder' methods instead of a 'setter' method.
     */
    @Builder
    public Mechanic(Long id, String firstName, String lastName, @Singular Set<Specialty> specialties) {
        super(id, firstName, lastName);
        if (specialties != null && specialties.size() > 0) {
            this.specialties = specialties;
        } else {
            this.specialties = new HashSet<>();
        }
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "mechanic_specialty", joinColumns = @JoinColumn(name = "mechanic_id"), inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();

}
