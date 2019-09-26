package com.moviesmustsee.businessServices;

import com.moviesmustsee.documents.Region;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RegionService {

    private final String[] EUROPA = {
            "Poland", "Portugal", "Romania", "Russia", "San Marino",
            "Serbia", "Slovakia", "Slovenia", "Spain", "Turkey",
            "Ukraine", "United Kingdom", "Sweden", "Switzerland", "Malta", "Moldova",
            "Monaco", "Montenegro", "Netherlands", "Georgia", "Germany", "Greece", "Finland",
            "France", "Estonia", "Denmark", "Croatia", "Cyprus", "Czechia", "Belarus", "Belgium",
            "Bosnia and Herzegovina", "Bulgaria", "Albania", "Andorra", "Armenia", "Austria", "Azerbaijan"};

    private final String[] LATIN_AMERICA = {
            "Argentina", "Bolivia", "Brazil", "Chile", "Colombia",
            "Ecuador", "French Guiana", "Guyana", "Paraguay",
            "Peru", "Suriname", "Uruguay", "Venezuela", "Belize", "Costa Rica",
            "El Salvador", "Guatemala", "Honduras", "Mexico", "Nicaragua", "Panama",
            "Antigua & Barbuda", "Aruba", "Bahamas", "Barbados", "Cayman Islands", "Cuba",
            "Dominica", "Dominican Republic", "Grenada", "Guadeloupe", "Haiti", "Jamaica",
            "Martinique", "Puerto Rico", "Trinidad & Tobago", "Virgin Islands"
    };

    private final String[] EAST_ASIA = {
            "China", "Hong Kong", "Japan",
            "Mongolia", "North Korea", "South Korea", "Taiwan"
    };

    private final String[] MIDDLE_EAST = {"Saudi Arabia", "Syria", "Turkey", "United Arab Emirates",
            "Yemen", "Bahrain", "Cyprus", "Egypt", "Iran", "Iraq", "Israel", "Jordan", "Kuwait",
            "Lebanon", "Oman", "Qatar"};

    private final String[] NORTH_AMERICA = {"United States", "Canada"};

    public RegionService() {
    }

    public Region getCountryRegion(String country) {
        Region region = Region.NONE;
        if (Arrays.asList(MIDDLE_EAST).contains(country)) {
            region = Region.MIDDLE_EAST;
        } else if (Arrays.asList(LATIN_AMERICA).contains(country)) {
            region = Region.LATIN_AMERICA;
        } else if (Arrays.asList(EUROPA).contains(country)) {
            region = Region.EUROPA;
        } else if (Arrays.asList(EAST_ASIA).contains(country)) {
            region = Region.EAST_ASIA;
        } else if (Arrays.asList(NORTH_AMERICA).contains(country)) {
            region = Region.NORTH_AMERICA;
        }
        return region;
    }

}
