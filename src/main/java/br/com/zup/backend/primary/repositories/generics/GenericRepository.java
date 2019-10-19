package br.com.zup.backend.primary.repositories.generics;

import br.com.zup.backend.primary.domain.common.Common;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/** Class implements common methods in all aplication but you need to extend Common class.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@NoRepositoryBean
public interface GenericRepository<T extends Common> extends
        CrudRepository<T, Long>, JpaSpecificationExecutor<T>,
        JpaRepository<T, Long> {

}
