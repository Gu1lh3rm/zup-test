package br.com.zup.backend.primary.resources.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class GenericResource<Type extends Common> implements GenericResourceImpl<Type> {
    private GenericService<Type> service;

    public GenericResource(GenericService<Type> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<?>> findAll() {

        return ResponseEntity.ok().body(service().findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Type> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service().findById(id));
    }

    @PostMapping
    public ResponseEntity<Type> create(@RequestBody Type entity) {
        return ResponseEntity.ok().body(service().save(entity));
    }

    @PutMapping("{id}")
    public ResponseEntity<Type> update(@PathVariable Long id, @RequestBody Type entity) {
        return ResponseEntity.ok().body(service().update(id,entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Type> delete(@PathVariable Long id, @RequestBody Type  entity) {
        service().delete(id, entity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<?>> findAll(
            @RequestParam(value = "key") String key,
            @RequestParam(value = "operation") String operation,
            @RequestParam(value = "value") String value) {
        return ResponseEntity.ok().body((List<Type>) service().findAll(key, operation, value));
    }


    public GenericService<Type> service() {
        return service;
    }

    public GenericRepository<Type> repository() {
        return service.repository();
    }
}
