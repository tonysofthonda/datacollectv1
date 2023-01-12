/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.validation.Alphabetic;
import java.io.Serializable;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VJC80587
 */
public class DcContactDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id;
    
    private String phoneNumber;
    
    @NotEmpty
    @Alphabetic
    private String firstName;
    
    @NotEmpty
    @Alphabetic
    private String lastName;
    
    @Alphabetic
    private String motherLastName;
    
    @NotEmpty
    @Email
    private String email;
    
    private String notes;
    
    private Long terchiefId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public Long getTerchiefId() {
        return terchiefId;
    }
    
    public void setTerchiefId(Long terchiefId) {
        this.terchiefId = terchiefId;
    }
}
