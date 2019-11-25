package br.com.api.backend.primary.repositories;

import br.com.api.backend.primary.repositories.generics.GenericRepository;
import br.com.api.backend.primary.domain.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AdressRepository extends GenericRepository<Address> {
}
