package br.com.zup.backend.primary.services.generics;

import br.com.zup.backend.primary.domain.common.Common;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Guilherme Lourenco
 * @version 1.00
 * @since Release 01 of the application
 */
public class SearchSpecificationsBuilder<C extends Common> {
    private final List<SearchCriteria> params;

    public SearchSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public SearchSpecificationsBuilder with(String key, String operation, String value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<C> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(SearchSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .or(specs.get(i));
        }
        return result;
    }
}
