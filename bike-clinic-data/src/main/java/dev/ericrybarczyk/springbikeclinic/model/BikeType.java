package dev.ericrybarczyk.springbikeclinic.model;

public class BikeType extends BaseEntity {

    private String modelName;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
