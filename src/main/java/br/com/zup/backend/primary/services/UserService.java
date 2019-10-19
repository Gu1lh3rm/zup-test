package br.com.zup.backend.primary.services;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.repositories.UserRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
