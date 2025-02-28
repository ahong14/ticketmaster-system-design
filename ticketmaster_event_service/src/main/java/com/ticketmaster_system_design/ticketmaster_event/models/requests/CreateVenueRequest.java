package com.ticketmaster_system_design.ticketmaster_event.models.requests;

public class CreateVenueRequest {
    private String description;

    private String location;

    private String coordinates;

    private Integer seats;

    public CreateVenueRequest() {}

    public CreateVenueRequest(String description, String location, String coordinates, Integer seats) {
        this.description = description;
        this.location = location;
        this.coordinates = coordinates;
        this.seats = seats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "CreateVenueRequest{" +
                "description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", coordinates='" + coordinates + '\'' +
                ", seats=" + seats +
                '}';
    }
}
