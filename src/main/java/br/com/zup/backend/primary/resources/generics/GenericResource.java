package br.com.zup.backend.primary.resources.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.generics.GenericService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/** Class exposes endpoints of all classes extending from common.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 * @param Type Class - Implements Class Entity
 */
public class GenericResource<Type extends Common> implements GenericResourceImpl<Type> {
    private GenericService<Type> service;

    /** Receive or service of type Type class.
     * @param ClassService Service - Implements ClassService of Entity
     */
    public GenericResource(GenericService<Type> service) {
        this.service = service;
    }

    /** Return List<Object> type the class.
     * @return ResponseEntity<List>
     */
    @GetMapping
    public ResponseEntity<List<?>> findAll() {

        return ResponseEntity.ok().body(service().findAll());
    }

    /** Return Object type the class
     * @param id Long
     * @return ResponseEntity<Class>
     */
    @GetMapping("{id}")
    public ResponseEntity<Type> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(service().findById(id));
    }

    /** Return Object type the class
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @PostMapping
    public ResponseEntity<Type> create(@Valid @RequestBody Type entity) {
        return ResponseEntity.ok().body(service().save(entity));
    }

    /** Return Object type the class
     * @param id in url
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @PutMapping("{id}")
    public ResponseEntity<Type> update(@Valid @PathVariable Long id, @Valid @RequestBody Type entity) {
        return ResponseEntity.ok().body(service().update(id,entity));
    }

    /** Return Object type the class
     * @param id in url
     * @param entity Type of Class
     * @return ResponseEntity<Class>
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Type> delete(@PathVariable Long id, @Valid @RequestBody Type  entity) {
        service().delete(id, entity);
        return ResponseEntity.noContent().build();
    }

    /** Received or service of type Type class.
     * @param key String - receiver tipes equal, ilike, like, >, <
     * @param operation String
     * @param value String
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<?>> findAll(
            @Valid @NonNull @NotBlank(message = "key cannot be null") @RequestParam(value = "key") String key,
            @Valid @NonNull @NotBlank(message = "operation cannot be null") @RequestParam(value = "operation") String operation,
            @Valid @NonNull @NotBlank(message = "value cannot be null") @RequestParam(value = "value") String value) {
        return ResponseEntity.ok().body(service().findAll(key, operation, value));
    }

    /** Received or service of type Type class.
     * @param ClassService Service - Implements ClassService of Entity
     * @return ClassService Service
     */
    public GenericService<Type> service() {
        return service;
    }

    /** Received or service of type Type class.
     * @param ClassRepository Service - Implements ClassRepository of Entity
     * @return ClassRepository Repository
     */
    public GenericRepository<Type> repository() {
        return service.repository();
    }
}
