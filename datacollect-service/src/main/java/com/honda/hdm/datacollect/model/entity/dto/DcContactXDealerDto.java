package com.honda.hdm.datacollect.model.entity.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class DcContactXDealerDto {

    private Long id;

    @NotNull
    private DcDealerDto dealer;
    @NotNull
    private DcContactDto contact;
    @NotNull
    private DcPositionDto position;
    @Size(min = 1)
    private List<DcSystemServiceDto> notifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DcDealerDto getDealer() {
        return dealer;
    }

    public void setDealer(DcDealerDto dealer) {
        this.dealer = dealer;
    }

    public DcContactDto getContact() {
        return contact;
    }

    public void setContact(DcContactDto contact) {
        this.contact = contact;
    }

    public DcPositionDto getPosition() {
        return position;
    }

    public void setPosition(DcPositionDto position) {
        this.position = position;
    }

    public List<DcSystemServiceDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<DcSystemServiceDto> notifications) {
        this.notifications = notifications;
    }
}
