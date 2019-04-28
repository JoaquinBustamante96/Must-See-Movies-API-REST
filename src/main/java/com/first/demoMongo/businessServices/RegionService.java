package com.first.demoMongo.businessServices;

import com.first.demoMongo.documents.Region;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegionService {

    public static final String[] EUROPA = {
            "Poland", "Portugal", "Romania", "Russia", "San Marino",
            "Serbia", "Slovakia", "Slovenia", "Spain", "Turkey",
            "Ukraine", "United Kingdom", "Sweden", "Switzerland", "Malta", "Moldova",
            "Monaco", "Montenegro", "Netherlands", "Georgia", "Germany", "Greece", "Finland",
            "France", "Estonia", "Denmark", "Croatia", "Cyprus", "Czechia", "Belarus", "Belgium",
            "Bosnia and Herzegovina", "Bulgaria", "Albania", "Andorra", "Armenia", "Austria", "Azerbaijan"};

    public static final String[] LATIN_AMERICA = {
            "Argentina", "Bolivia", "Brazil", "Chile", "Colombia",
            "Ecuador", "French Guiana", "Guyana", "Paraguay",
            "Peru", "Suriname", "Uruguay", "Venezuela", "Belize", "Costa Rica",
            "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama",
            "Antigua & Barbuda", "Aruba", "Bahamas", "Barbados", "Cayman Islands", "Cuba",
            "Dominica", "Dominican Republic", "Grenada", "Guadeloupe", "Haiti", "Jamaica",
            "Martinique", "Puerto Rico", "Trinidad & Tobago", "Virgin Islands"
    };

    public static final String[] EAST_ASIA = {
            "China", "Hong Kong", "Japan",
            "Mongolia", "North Korea", "South Korea", "Taiwan"
    };

    public static final String[] MIDDLE_EAST = {"Saudi Arabia", "Syria", "Turkey", "United Arab Emirates",
            "Yemen", "Bahrain", "Cyprus", "Egypt", "Iran", "Iraq", "Israel", "Jordan", "Kuwait",
            "Lebanon", "Oman", "Qatar"};

    public static final String[] NORTH_AMERICA = {"United States", "Canada"};

    public RegionService() {
    }

    public Region getCountryRegion(String country) {
        Region region = Region.NONE;
        if (Arrays.asList(RegionService.MIDDLE_EAST).contains(country)) {
            region = Region.MIDDLE_EAST;
        } else if (Arrays.asList(RegionService.LATIN_AMERICA).contains(country)) {
            region = Region.LATIN_AMERICA;
        } else if (Arrays.asList(RegionService.EUROPA).contains(country)) {
            region = Region.EUROPA;
        } else if (Arrays.asList(RegionService.EAST_ASIA).contains(country)) {
            region = Region.EAST_ASIA;
        } else if (Arrays.asList(RegionService.NORTH_AMERICA).contains(country)) {
            region = Region.NORTH_AMERICA;
        }
        return region;
    }

}
