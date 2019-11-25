package br.com.api.backend.primary.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/** Class for common objects for correct operation you need to extend all classes, if you want to use the generics metods.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public abstract class Common implements Serializable {
    private static final long serialVersionUID = 1L;

    public Common() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(columnDefinition = "boolean DEFAULT false")
    protected Boolean deleted = false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE DEFAULT now()")
    protected Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    protected Date updated = new Date();


    @PrePersist
    public void prePersist() {
        created = new Date();
        updated = null;
    }

    @PreUpdate
    public void preUpdate() {
        updated = new Date();
    }

    /** Return id PK
     * @return id Long*/
    public Long getId() {
        return id;
    }

    /** Set id PK
     * @param id Long*/
    public void setId(Long id) {
        this.id = id;
    }

    /** Return true or false for logical deletion
     * if this you recover false this object will not be available in the application
     * @return deleted Boolean*/
    public Boolean getDeleted() {
        return deleted;
    }

    /** Set true or false for logical deletion
     * if this you set false this object will not be available in the application
     * @param deleted Boolean*/
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /** Return return the exact moment of class insertion
     * @return created Date*/
    public Date getCreated() {
        return created;
    }

    /** Set saves the exact moment of class insertion and this object cannot be updated
     * @param created Date(TemporalType.TIMESTAMP)*/
    public void setCreated(Date created) {
        this.created = created;
    }

    /** Return the last date when this class was updated
     * @return updated Date(TemporalType.TIMESTAMP)*/
    public Date getUpdated() {
        return updated;
    }

    /** Set saves the exact moment of class updated
     * @param updated Date(TemporalType.TIMESTAMP)*/
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Common)) return false;
        Common that = (Common) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
