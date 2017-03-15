package com.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * @author firoz
 * @since 15/03/17
 */
public class FileComplaint {

    private final String id;
    private final String company;
    private final String description;

    @JsonCreator
    public FileComplaint(@JsonProperty("company") String company, @JsonProperty("description") String description) {
        this.id = UUID.randomUUID().toString();
        this.company = company;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }
}