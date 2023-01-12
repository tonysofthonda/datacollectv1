package com.honda.hdm.datacollect.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;


@Entity
@Table(name = "DC_VIEW_ACTION", 
		uniqueConstraints = { @UniqueConstraint(columnNames = {"SHORT_NAME"})}
)
@XmlRootElement
public class DcViewAction extends AuditableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcmenu_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "SHORT_NAME", nullable = false, length = 50)
    private String shortName;
    
    @Basic(optional = false)
    @Column(name = "FRIENDLY_NAME", nullable = false, length = 50)
    private String friendlyName;
        
    @JoinColumn(name = "DC_VIEW_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcView view;
    
    @JoinTable(name = "DC_VACTION_X_ROLE", joinColumns = {
        @JoinColumn(name = "DC_VIEW_ACTION_ID", referencedColumnName = "ID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "DC_ROLE_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany
    private List<DcRole> assignedRoleList;
    
    public DcViewAction() {
    }

    public DcViewAction(Long id) {
        this.id = id;
    }    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

	@XmlTransient
	public DcView getView() {
		return view;
	}

	public void setView(DcView view) {
		this.view = view;
	}	

	@XmlTransient
	public List<DcRole> getAssignedRoleList() {
		return assignedRoleList;
	}

	public void setAssignedRoleList(List<DcRole> assignedRoleList) {
		this.assignedRoleList = assignedRoleList;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcViewAction)) {
            return false;
        }
        DcViewAction other = (DcViewAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.auth.DcMenu[ id=" + id + " ]";
    }
    
}
