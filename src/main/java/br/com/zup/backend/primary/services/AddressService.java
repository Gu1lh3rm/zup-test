package br.com.zup.backend.primary.services;

import br.com.zup.backend.primary.domain.Address;
import br.com.zup.backend.primary.repositories.AdressRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends GenericService<Address> {

    @Autowired
    public AddressService(AdressRepository repository) {
        super(repository);
    }

    @Autowired
    public AdressRepository adressRepository;

    @Override
    public Address save(Address entity) {
        isNotNull(entity.getUser(), "user");
        isNotNull(entity.getUser().getId(), "user id");
        verifyUserSS(entity.getUser().getId());
        return super.save(entity);
    }

    @Override
    public Address update(Long id, Address entity) {
        isNotNull(entity.getUser(), "user");
        isNotNull(entity.getUser().getId(), "user id");
        verifyUserSS(entity.getUser().getId());
        return super.update(id, entity);
    }

    @Override
    public Address delete(Long id, Address entity) {
        verifyUserSS(id);
        return super.delete(id, entity);
    }
}
