package br.com.api.backend.primary.resources.generics;

import br.com.api.backend.primary.repositories.generics.GenericRepository;
import br.com.api.backend.primary.domain.common.Common;
import br.com.api.backend.primary.services.generics.GenericService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GenericResourceImpl<C extends Common> {

    public ResponseEntity<List<?>> findAll();

    public ResponseEntity<C> findById(Long id);

    public ResponseEntity<C> create(C entity);

    public ResponseEntity<C> update(Long id, C entity);

    public ResponseEntity<C> delete(Long id, C entity);

    public GenericService<C> service();

    public GenericRepository<C> repository();


}
