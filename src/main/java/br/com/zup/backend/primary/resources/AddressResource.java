package br.com.zup.backend.primary.resources;

import br.com.zup.backend.primary.domain.Address;
import br.com.zup.backend.primary.repositories.AdressRepository;
import br.com.zup.backend.primary.resources.generics.GenericResource;
import br.com.zup.backend.primary.services.AddressService;
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
@RequestMapping(value = "/addresses")
public class AddressResource extends GenericResource<Address> {

    @Autowired
    public AdressRepository repository;

    public AddressResource(AddressService service) {
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
