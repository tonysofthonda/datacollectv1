package com.honda.hdm.datacollect.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;


@Entity
@Table(name = "DC_VIEW", 
		uniqueConstraints = { @UniqueConstraint(columnNames = {"NAME", "Z_ORDER"})}
)
@XmlRootElement
public class DcView extends AuditableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcmenu_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    
    @Basic(optional = false)
    @Column(name = "FRIENDLY_NAME", nullable = false, length = 50)
    private String friendlyName;
    
    @Basic(optional = false)
    @Column(name = "ROUTE", nullable = false, length = 255)
    private String route;    
    
    @Column(name = "Z_ORDER")
    private Integer order;
    
    @JoinColumn(name = "DC_MENU_CATEGORY_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcMenuCategory menuCategory;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "view")
    private List<DcViewAction> viewActions;
    
    public DcView() {
    }

    public DcView(Long id) {
        this.id = id;
    }

    public DcView(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getFriendlyName() {
		return friendlyName;
	}

	public void setFriendlyName(String friendlyName) {
		this.friendlyName = friendlyName;
	}

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }     
    
    public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	@XmlTransient
    public DcMenuCategory getMenuCategory() {
		return menuCategory;
	}

	public void setMenuCategory(DcMenuCategory menu) {
		this.menuCategory = menu;
	}

	@XmlTransient
	public List<DcViewAction> getViewActions() {
		return viewActions;
	}

	public void setViewActions(List<DcViewAction> viewActions) {
		this.viewActions = viewActions;
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
        if (!(object instanceof DcView)) {
            return false;
        }
        DcView other = (DcView) object;
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
