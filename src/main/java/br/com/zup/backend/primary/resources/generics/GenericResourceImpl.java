package br.com.zup.backend.primary.resources.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.List;

public interface GenericResourceImpl<C extends Common> {

    public ResponseEntity<List<?>> findAll();

    public ResponseEntity<C> findById(Long id);

    public ResponseEntity<C> create(@Valid C entity);

    public ResponseEntity<C> update(Long id, @Valid C entity);

    public ResponseEntity<C> delete(Long id, @Valid C entity);

    public GenericService<C> service();

    public GenericRepository<C> repository();


}
