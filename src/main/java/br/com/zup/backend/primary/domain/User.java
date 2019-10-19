package br.com.zup.backend.primary.domain;

import br.com.zup.backend.primary.domain.common.Common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/** Class for saving user information and will be used for basic authentication.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@Entity
public class User extends Common {

    @NotNull(message = "Email cannot be null")
    @Column(unique = true)
    private String email;

    @NotNull(message = "Social Code cannot be null")
    @Column(unique = true)
    private Long socialCode;

    @NotNull(message = "Birth Date cannot be null")
    @Column(unique = true)
    private Date birthDate;

    public User() {

    }

    public User(@NotNull(message = "Name cannot be null") String name, String description, @NotNull(message = "Email cannot be null") String email, @NotNull(message = "Social Code cannot be null") Long socialCode, @NotNull(message = "Birth Date cannot be null") Date birthDate) {
        super(name, description);
        this.email = email;
        this.socialCode = socialCode;
        this.birthDate = birthDate;
    }

    /** Return Email of this object is using for authentication
     *  the Email is unique
     * @return email String*/
    public String getEmail() {
        return email;
    }

    /** Set Email of this object is using for authentication
     * the Email is unique
     * @param email String*/
    public void setEmail(String email) {
        this.email = email;
    }

    /** Return owner Social Code of this object
     * @return socialCode String*/
    public Long getSocialCode() {
        return socialCode;
    }

    /** Set owner Social Code of this object
     * @param socialCode String*/
    public void setSocialCode(Long socialCode) {
        this.socialCode = socialCode;
    }

    /** Return owner Birth Date of this object
     * @return birthDate Date*/
    public Date getBirthDate() {
        return birthDate;
    }

    /** Set owner Birth Date of this object
     * @param birthDate Date*/
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
