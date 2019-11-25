package br.com.api.backend.primary.domain.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/** Class for common objects for correct operation you need to extend all classes, if you want to use the generics metods.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public abstract class Basics extends Common {
    private static final long serialVersionUID = 1L;

    public Basics() {
    }

    public Basics(@NotNull String name, String description) {
        this.name = name;
        this.description = description;
    }

    @NotNull(message = "Name cannot be null")
    public String name;

    public String description;

    /** Return owner name of this object
     * @return name String*/
    public String getName() {
        return name;
    }

    /** Set owner name of this object
     * @param name String*/
    public void setName(String name) {
        this.name = name;
    }

    /** Return description general of this object
     * @return description String*/
    public String getDescription() {
        return description;
    }

    /** Set description general of this object
     * @param description String*/
    public void setDescription(String description) {
        this.description = description;
    }

}
