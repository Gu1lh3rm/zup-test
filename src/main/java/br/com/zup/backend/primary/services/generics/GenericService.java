package br.com.zup.backend.primary.services.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.exceptions.ObjectNotFoundException;

import java.util.List;

/** Class implements common methods in all aplication but you need to extend Common class.
 * This class implements GenericServiceImpl and all Services the other repositories
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
public class GenericService<C extends Common> implements GenericServiceImpl<C> {
    private GenericRepository<C> repository;

    public GenericService(GenericRepository<C> repository) {
        this.repository = repository;
    }

    @Override
    public List<C> findAll() {
        return repository().findAll();
    }

    @Override
    public C findById(Long id) {
        return findByIdObjectNotFound(id);
    }

    @Override
    public C save(C entity) {
        repository().save(entity);
        return findByIdObjectNotFound(entity.getId());
    }

    @Override
    public C update(Long id, C entity) {
        acceptedCriteria(id, entity);
        repository().save(entity);
        return findByIdObjectNotFound(entity.getId());
    }

    @Override
    public C delete(Long id, C entity) {
        acceptedCriteria(id, entity);
        repository().delete(entity);
        return null;
    }

    @Override
    public List<C> findAll(String key, String operation, String value) {
        key = key != null ? !key.equals("") ? key : "name" : "name";
        operation = operation != null ? !operation.equals("") ? operation : "like" : "like";
        value = value != null ? !value.equals("") ? value : "" : "";

        SearchSpecification<C> spec
                = new SearchSpecification(new SearchCriteria(key, operation, value));

        return repository().findAll(spec);
    }

    @Override
    public GenericRepository<C> repository() {
        return repository;
    }

    private void acceptedCriteria(Long id, C entity) {
        if(id.equals(entity.getId())) {
            findByIdObjectNotFound(entity.getId());
        } else {
            acceptedCriteriaException(id, entity);
        }
    }

    private C findByIdObjectNotFound(Long id) {
        return repository().findById(id).orElseThrow(() -> objectIdNotFound(id));
    }

    private C objectNotFoundException(Long id) {
        throw objectIdNotFound(id);
    }

    private ObjectNotFoundException objectIdNotFound(Long id) {
        return  new ObjectNotFoundException(
                "Object not found! Id: " + id);
    }

    private void acceptedCriteriaException(Long id, C entity) {
        throw new ObjectNotFoundException(
                "Objects must be the same! Url Id: " + id + " and Class Id: " + entity.getId());
    }
}
