package ua.com.platinumbank.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class represents both single search result (Elasticsearch hit) and search request.
 */
public class Address {

    private static final Address EMPTY_ADDRESS;

    private String region;
    private String district;
    private String city;
    private String postIndex;
    private String street;
    private String[] house;

    // Initialize Address instance with empty fields
    // This instance will be returned to caller instead of actual search results in case of any
    // error during search
    static {
        EMPTY_ADDRESS = new Address();
    }

    public Address() {
        this.region = "";
        this.district = "";
        this.city = "";
        this.postIndex = "";
        this.street = "";
        this.house = new String[0];
    }

    public String getRegion() {

        return region;
    }

    public Address setRegion(String region) {

        this.region = region != null ? region : "";
        return this;
    }

    public String getDistrict() {

        return district;
    }

    public Address setDistrict(String district) {

        this.district = district != null ? district : "";
        return this;
    }

    public String getCity() {

        return city;
    }

    public Address setCity(String city) {

        this.city = city != null ? city : "";
        return this;
    }

    public String getPostIndex() {

        return postIndex;
    }

    public Address setPostIndex(String postIndex) {

        this.postIndex = postIndex != null ? postIndex : "";
        return this;
    }

    public String getStreet() {

        return street;
    }

    public Address setStreet(String street) {

        this.street = street != null ? street : "";
        return this;
    }

    public String[] getHouse() {

        return house;
    }

    // This method should not be used by Jackson during auto generating Address object
    // It is used for deserialization JSON request from caller
    @JsonIgnore
    public String getHouseRequest() {

        return house[0];
    }

    public Address setHouse(String house) {

        if (house != null) {
            // Remove any whitespaces (if any) between house numbers
            house = house.replaceAll("\\s", "");

            this.house = house.split(",");
        } else {
            this.house = new String[1];
            this.house[0] = "";
        }

        return this;
    }

    // Returns empty instance
    public static Address getEmptyAddress() {

        return EMPTY_ADDRESS;
    }

    @Override
    public String toString() {

        // @formatter:off
        return "Address{" +
            "region='" + region + '\'' +
            ", district='" + district + '\'' +
            ", city='" + city + '\'' +
            ", postIndex='" + postIndex + '\'' +
            ", street='" + street + '\'' +
            ", house=" + Arrays.toString(house) +
            '}';
        // @formatter:on
    }
}
