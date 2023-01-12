package com.honda.hdm.datacollect.model.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "DC_CONTACT_X_DEALER")
@XmlRootElement
public class DcContactXDealer {
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dc_contact_x_dealer_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @Column(name = "DC_DEALER_ID")
    private Long dealerId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DC_CONTACT_ID", referencedColumnName = "ID")
    private DcContact contact;
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "DC_POSITION_ID", referencedColumnName = "ID")
//    private DcPosition position;

//    @JoinTable(name = "DC_SYS_SERV_X_CONTACT_DEALER",
//            joinColumns = {
//                    @JoinColumn(name = "DC_CONTACT_X_DEALER_ID", referencedColumnName = "ID", nullable = false)},
//            inverseJoinColumns = {
//                    @JoinColumn(name = "DC_SYSTEM_SERVICE_ID", referencedColumnName = "ID", nullable = false)})
//    @ManyToMany(cascade = CascadeType.MERGE)
//    private List<DcSystemService> notifications;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public DcContact getContact() {
        return contact;
    }

    public void setContact(DcContact contact) {
        this.contact = contact;
    }

//    public DcPosition getPosition() {
//        return position;
//    }
//
//    public void setPosition(DcPosition position) {
//        this.position = position;
//    }
//
//    public List<DcSystemService> getNotifications() {
//        return notifications;
//    }
//
//    public void setNotifications(List<DcSystemService> notifications) {
//        this.notifications = notifications;
//    }
}
