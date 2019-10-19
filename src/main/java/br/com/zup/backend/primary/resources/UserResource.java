package br.com.zup.backend.primary.resources;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.repositories.UserRepository;
import br.com.zup.backend.primary.resources.generics.GenericResource;
import br.com.zup.backend.primary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
