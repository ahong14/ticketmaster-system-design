package com.ticketmaster_system_design.ticketmaster_event.models.requests;

public class CreatePerformerRequest {
    private String name;

    private String description;

    public CreatePerformerRequest() {}

    public CreatePerformerRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreatePerformer{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
