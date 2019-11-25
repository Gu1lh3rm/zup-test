package br.com.api.backend.primary.repositories;

import br.com.api.backend.primary.repositories.generics.GenericRepository;
import br.com.api.backend.primary.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends GenericRepository<User> {
    User findByEmail(String email);
    User findBySocialCode(Long email);
}
