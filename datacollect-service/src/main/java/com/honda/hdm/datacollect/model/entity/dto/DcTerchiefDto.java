/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.validation.Alphabetic;
import com.honda.hdm.datacollect.validation.Alphanumeric;
import java.io.Serializable;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VJC80587
 */
public class DcTerchiefDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @NotEmpty
    @Alphabetic
    private String firstName;
    
    @Length(max = 50)
    @NotEmpty
    @Alphabetic
    private String lastName;
    
    @Length(max = 50)
    @Alphabetic
    private String motherLastName;
    
    @Length(max = 50)
    @Alphanumeric
    private String notes;

    public DcTerchiefDto() {
    }

    public DcTerchiefDto(String firstName) {
        this.firstName = firstName;
    }

    public DcTerchiefDto(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
