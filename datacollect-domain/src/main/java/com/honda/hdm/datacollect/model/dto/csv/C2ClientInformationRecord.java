package com.honda.hdm.datacollect.model.dto.csv;

import java.math.BigInteger;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Aug 29, 2018
 *
 * Client Information Record. Key: C2 Required: true Occurrence: 1
 *
 */
@Record(name = "C2ClientInformationRecord", minOccurs = 1, maxOccurs = 1, order = 1)
@Fields({
    @Field(literal = "C2", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class C2ClientInformationRecord extends BaseBeanIoRecord {

    @Field(at = 1, required = false)
    private String addrStreet;
    
    @Field(at = 2, required = false)
    private String addrNeighborhood;

    @Field(at = 3,required = false)
    private String addrIntNum;
    
    @Field(at = 4, required = false)
    private String addrExtNum;

    @Field(at = 5, required = false)
    private String addrPostCode;
    
    @Field(at = 6, required = false)
    private String addrState;
    
    @Field(at = 7, required = false)
    private String email;
    
    @Field(at = 8, required = false)
    private String phone;
    
    @Field(at = 9, required = false)
    private String gender;
    
    @Field(at = 10, required = false)
    private String firstName;

    @Field(at = 11, required = false)
    private String lastName;

    @Field(at = 12, required = false)
    private String lastNameB;

    @Field(at = 13, required = false)
    private String privacy;
    
    private BigInteger idState ;

    public BigInteger getIdState() {
        return idState;
    }

    public void setIdState(BigInteger idState) {
        this.idState = idState;
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

    public String getLastNameB() {
        return lastNameB;
    }

    public void setLastNameB(String lastNameB) {
        this.lastNameB = lastNameB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy != null ? privacy.trim() : privacy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrExtNum() {
        return addrExtNum;
    }

    public void setAddrExtNum(String addrExtNum) {
        this.addrExtNum = addrExtNum;
    }

    public String getAddrIntNum() {
        return addrIntNum;
    }

    public void setAddrIntNum(String addrIntNum) {
        this.addrIntNum = addrIntNum;
    }

    public String getAddrNeighborhood() {
        return addrNeighborhood;
    }

    public void setAddrNeighborhood(String addrNeighborhood) {
        this.addrNeighborhood = addrNeighborhood;
    }

    public String getAddrPostCode() {
        return addrPostCode;
    }

    public void setAddrPostCode(String addrPostCode) {
        this.addrPostCode = addrPostCode;
    }

    public String getAddrState() {
        return addrState;
    }

    public void setAddrState(String addrState) {
        this.addrState = addrState;
    }

    @Override
    public String toString() {
        return "C2ClientInformationRecord{" + "addrStreet=" + addrStreet + ", addrNeighborhood=" + addrNeighborhood + ", addrIntNum=" + addrIntNum + ", addrExtNum=" + addrExtNum + ", addrPostCode=" + addrPostCode + ", addrState=" + addrState + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", firstName=" + firstName + ", lastName=" + lastName + ", lastNameB=" + lastNameB + ", privacy=" + privacy + '}';
    }

    
}
