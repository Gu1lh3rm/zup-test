package br.com.zup.backend.primary.resources.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;
import br.com.zup.backend.primary.services.generics.GenericService;

import javax.validation.Valid;
import java.util.List;

public interface GenericResourceImpl<C extends Common> {

    public List<C> findAll();

    public C findById(Long id);

    public C create(@Valid C entity);

    public C update(@Valid C entity);

    public C delete(Long id);

    public GenericService<C> service();

    public GenericRepository<C> repository();


}
