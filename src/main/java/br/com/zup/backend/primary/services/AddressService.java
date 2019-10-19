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

}
