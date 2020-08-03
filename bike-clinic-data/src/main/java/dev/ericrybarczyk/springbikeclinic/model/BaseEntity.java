package dev.ericrybarczyk.springbikeclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass // mapping info applied to subclasses, but no persistence table for this class
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Hibernate recommends the boxed type for null support

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
