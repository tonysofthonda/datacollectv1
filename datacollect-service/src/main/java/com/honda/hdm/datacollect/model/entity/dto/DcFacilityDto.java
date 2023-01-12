package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.validation.Alphanumeric;
import org.hibernate.validator.constraints.NotEmpty;

public class DcFacilityDto {

    private Long id;

    @NotEmpty
    @Alphanumeric
    private String concept;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
