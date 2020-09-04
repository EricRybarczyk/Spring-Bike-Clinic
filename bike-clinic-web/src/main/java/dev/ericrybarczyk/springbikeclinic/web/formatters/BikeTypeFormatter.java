package dev.ericrybarczyk.springbikeclinic.web.formatters;

import dev.ericrybarczyk.springbikeclinic.model.BikeType;
import dev.ericrybarczyk.springbikeclinic.services.BikeTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class BikeTypeFormatter implements Formatter<BikeType> {

    private final BikeTypeService bikeTypeService;

    public BikeTypeFormatter(BikeTypeService bikeTypeService) {
        this.bikeTypeService = bikeTypeService;
    }

    @Override
    public BikeType parse(String text, Locale locale) throws ParseException {
        Optional<BikeType> optionalBikeType = bikeTypeService.findAll()
                .stream()
                .filter(e -> e.getName().equals(text))
                .findFirst();
        if (optionalBikeType.isEmpty()) {
            throw new ParseException("Bike Type not found: " + text, 0);
        }
        return optionalBikeType.get();
    }

    @Override
    public String print(BikeType bikeType, Locale locale) {
        return bikeType.getName();
    }

}
