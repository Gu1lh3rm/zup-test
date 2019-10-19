package br.com.zup.backend.primary.services.generics;

import br.com.zup.backend.primary.domain.common.Common;
import br.com.zup.backend.primary.repositories.generics.GenericRepository;

import java.util.List;

public interface GenericServiceImpl <C extends Common> {
    List<C> findAll();

    C findById(Long id);

    C save(C entity);

    C update(Long id,C entity);

    C delete(Long id,C entity);

    List<C> findAll(String key, String operation, String value);

    public GenericRepository<C> repository();
}
