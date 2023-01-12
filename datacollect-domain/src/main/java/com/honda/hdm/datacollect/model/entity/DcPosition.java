package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DC_POSITION",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"JOB_ID"})
        })
@XmlRootElement
public class DcPosition extends RecordStatusableEntry implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @SequenceGenerator(name = "seq", sequenceName = "position_id_increment", allocationSize = 1)
        @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
        @Basic(optional = false)
        @Column(name = "ID", nullable = false, precision = 38, scale = 0)
        private Long id;

        @Column(name = "NAME_DESC", nullable = false)
        private String nameDescription;

        @Column(name = "JOB_ID", nullable = false, length = 100)
        private String jobId;

        @JoinTable(name = "DC_POSITION_X_ROLE",
                joinColumns = {
                        @JoinColumn(name = "DC_POSITION_ID", referencedColumnName = "ID", nullable = false)},
                inverseJoinColumns = {
                        @JoinColumn(name = "DC_ROLE_ID", referencedColumnName = "ID", nullable = false)})
        @ManyToMany(cascade = CascadeType.MERGE)
        private List<DcRole> roles;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNameDescription() {
                return nameDescription;
        }

        public void setNameDescription(String nameDescription) {
                this.nameDescription = nameDescription;
        }

        public String getJobId() {
                return jobId;
        }

        public void setJobId(String jobId) {
                this.jobId = jobId;
        }

        public List<DcRole> getRoles() {
                return roles;
        }

        public void setRoles(List<DcRole> roles) {
                this.roles = roles;
        }
}