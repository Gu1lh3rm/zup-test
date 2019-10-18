package br.com.zup.backend.primary.domain;

import br.com.zup.backend.primary.domain.common.Common;

import javax.validation.constraints.NotNull;
import java.util.Date;

/** Class for saving user information and will be used for basic authentication.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
public class User extends Common {

    private Long socialCode;

    private Date birthDate;

    public User(@NotNull String name, String description, Long socialCode, Date birthDate) {
        super(name, description);
        this.socialCode = socialCode;
        this.birthDate = birthDate;
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
