package dev.ericrybarczyk.springbikeclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
    private Long id; // Hibernate recommends the boxed type for null support

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
