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
    public List<Type> findAll() {
        return service().findAll();
    }

    @GetMapping("{id}")
    public Type findById(@PathVariable("id") Long id) {
        return service().findById(id);
    }

    @PostMapping
    public Type create(@RequestBody Type entity) {
        return service().save(entity);
    }

    @PutMapping("{id}")
    public Type update(@RequestBody Type entity) {
        return service().save(entity);
    }

    @DeleteMapping(value = "/{id}")
    public Type delete(@PathVariable Long id) {
        service().delete(id);
        return null;
    }

    @DeleteMapping
    public Type delete(@RequestBody Type  entity) {
        service().delete(entity);
        return null;
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Type>> findAll(
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
