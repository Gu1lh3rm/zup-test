package br.com.zup.backend.primary.repositories;

import br.com.zup.backend.primary.domain.User;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends GenericRepository<User> {
    User findByEmail(String email);
    User findBySocialCode(Long email);
}
