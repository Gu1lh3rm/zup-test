package br.com.api.backend.primary.services;

import br.com.api.backend.primary.repositories.UserRepository;
import br.com.api.backend.primary.services.generics.GenericService;
import br.com.api.backend.primary.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService extends GenericService<User> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    @Autowired
    public UserRepository userRepository;

    public User findByEmail(String obj) {
        return userRepository.findByEmail(obj);
    }
    public User findBySocialCode(Long obj) {
        return userRepository.findBySocialCode(obj);
    }

    @Override
    public User save(User entity) {
        isNotNull(entity.getPassword(), "Password");
        entity.setPassword(getEncoder().encode(entity.getPassword()));

        super.save(entity);

        entity.setPassword(null);
        return entity;
    }

    @Override
    public User findById(Long id) {
        verifyUserSS(id);
        return super.findById(id);
    }

    @Override
    public User update(Long id, User entity) {
        verifyUserSS(id);
        return super.update(id, fromUpdate(entity));
    }

    @Override
    public User delete(Long id, User entity) {
        verifyUserSS(id);
        return super.delete(id, entity);
    }

    public User fromUpdate(User entity) {
        User user = findById(entity.getId());
        if(entity.getPassword() == null) {
            entity.setPassword(user.getPassword());
        }

        return entity;

    }
}
