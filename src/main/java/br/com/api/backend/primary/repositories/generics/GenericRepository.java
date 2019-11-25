package br.com.api.backend.primary.repositories.generics;

import br.com.api.backend.primary.domain.common.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/** Class implements common methods in all aplication but you need to extend Common class.
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
@NoRepositoryBean
public interface GenericRepository<T extends Common> extends
         JpaSpecificationExecutor<T>,
        JpaRepository<T, Long> {

}
