package dev.ericrybarczyk.springbikeclinic.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"dev.ericrybarczyk.springbikeclinic"})
public class SpringBikeClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBikeClinicApplication.class, args);
    }

}
