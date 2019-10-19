package br.com.zup.backend.primary.services.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;

import java.util.List;
import java.util.Optional;

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
        Optional<C> obj = repository().findById(id);
        return obj.orElse(null);
    }

    @Override
    public C save(C entity) {
        return repository().save(entity);
    }

    @Override
    public C delete(Long id) {
        repository().deleteById(id);
        return null;
    }

    @Override
    public C delete(C entity) {
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
}
