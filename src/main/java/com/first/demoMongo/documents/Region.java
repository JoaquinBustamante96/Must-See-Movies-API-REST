package com.first.demoMongo.documents;

public enum Region {

    MIDDLE_EAST, LATIN_AMERICA, EUROPA, EAST_ASIA, NORTH_AMERICA, NONE;

    public String RegionName() {
        String region;
        switch (this) {
            case MIDDLE_EAST:
                region = "Middle East";
                break;
            case EUROPA:
                region = "Europa";
                break;
            case EAST_ASIA:
                region = "East Asia";
                break;
            case NORTH_AMERICA:
                region = "North America";
                break;
            case LATIN_AMERICA:
                region = "Latin America";
                break;
            default:
               region = "NONE";
    }
        return "RegionService " + region;
    }

}
