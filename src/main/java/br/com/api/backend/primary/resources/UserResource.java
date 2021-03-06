package br.com.api.backend.primary.resources;

import br.com.api.backend.primary.repositories.UserRepository;
import br.com.api.backend.primary.resources.generics.GenericResource;
import br.com.api.backend.primary.domain.User;
import br.com.api.backend.primary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** Class exposes endpoints of all classes extending from common.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@RestController
@RequestMapping(value = "/users")
public class UserResource extends GenericResource<User> {

    @Autowired
    public UserRepository repository;

    public UserResource(UserService service) {
        super(service);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    public ResponseEntity<List<?>> findAll() {
        return super.findAll();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @Override
    public ResponseEntity<List<?>> findAll(String key, String operation, String value) {
        return super.findAll(key, operation, value);
    }
}
