package br.com.zup.backend.primary.services.generics;

import java.text.Normalizer;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.zup.backend.primary.domain.common.Common;
import org.springframework.data.jpa.domain.Specification;

public class SearchSpecification<C extends Common> implements Specification<C> {

    private SearchCriteria criteria;

    public SearchSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<C> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":") || criteria.getOperation().equalsIgnoreCase("like")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("ilike")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        criteriaBuilder.function("unaccent", String.class,
                                criteriaBuilder.lower(
                                        root.get(criteria.getKey())
                                )
                        ), "%" + removeAccents(criteria.getValue().toLowerCase()) + "%");

            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }

    public static String removeAccents(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        return str;
    }

    @Override
    public Specification<C> and(Specification<C> other) {
        return Specification.super.and(other); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Specification<C> or(Specification<C> other) {
        return Specification.super.or(other); //To change body of generated methods, choose Tools | Templates.
    }
}
