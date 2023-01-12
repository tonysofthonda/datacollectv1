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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;

@Entity
@Table(name = "DC_MENU_CATEGORY", uniqueConstraints = { @UniqueConstraint(columnNames = { "NAME" }) })
@XmlRootElement
public class DcMenuCategory extends AuditableEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq", sequenceName = "dcmencat_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false, precision = 38, scale = 0)
	private Long id;

	@Basic(optional = false)
	@Column(name = "NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "Z_ORDER")
	private Integer order;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menuCategory")
	@OrderBy("order ASC")
	private List<DcView> views;

	public DcMenuCategory() {
	}

	public DcMenuCategory(Long id) {
		this.id = id;
	}

	public DcMenuCategory(Long id, String name) {
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@XmlTransient
	public List<DcView> getViews() {
		return views;
	}

	public void setViews(List<DcView> views) {
		this.views = views;
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
		if (!(object instanceof DcMenuCategory)) {
			return false;
		}
		DcMenuCategory other = (DcMenuCategory) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "com.honda.hdm.datacollect.model.entity.auth.DcMenuCategory[ id=" + id + " ]";
	}

}
