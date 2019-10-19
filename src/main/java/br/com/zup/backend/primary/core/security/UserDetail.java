package br.com.zup.backend.primary.core.security;

import br.com.zup.backend.primary.domain.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

public class UserDetail extends User {

    private Long id;
    private String name;
    private String email;
    private Set<Role> roles;

    public UserDetail(Long userId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = userId;
    }

    public UserDetail(UserSS credential, Collection<? extends GrantedAuthority> authorities, Set<Role> roles) {
        super(credential.getUsername(), credential.getPassword(), authorities);
        this.id = credential.getId();
//        TODO fix it
        this.name = credential.getUsername();
        this.email = credential.getUsername();
        this.roles = roles;
    }

    public Long getUserId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.id = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}

