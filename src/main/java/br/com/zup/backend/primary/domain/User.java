package br.com.zup.backend.primary.domain;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.domain.enums.UserType;
import br.com.zup.backend.primary.services.validation.UserInput;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** Class for saving user information and will be used for basic authentication.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@UserInput
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

    @NotNull(message = "User Type cannot be null")
    private Integer userType;

    public User() {

    }

    /** Return Email of this object is using for authentication
     *  the Email is unique
     * @return email String*/
    public String getEmail() {
        return email;
    }

    /** Constructor for simplify Creation of Object User
     * @param name String not null
     * @param description String
     * @param email String not null
     * @param birthDate Date
     * @return new User() User
     */
    public User(@NotNull(message = "Name cannot be null") String name,
                String description,
                @NotNull(message = "Email cannot be null") String email,
                @NotNull(message = "Social Code cannot be null") Long socialCode,
                @NotNull(message = "Birth Date cannot be null") Date birthDate,
                UserType userType) {
        super(name, description);
        this.email = email;
        this.socialCode = socialCode;
        this.birthDate = birthDate;
        this.userType = (userType==null) ? UserType.PEOPLE.getCod() : userType.getCod();
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
