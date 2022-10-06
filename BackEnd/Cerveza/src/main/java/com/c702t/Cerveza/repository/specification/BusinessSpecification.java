package com.c702t.Cerveza.repository.specification;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class BusinessSpecification {

    public Specification<BusinessEntity> getByFilters(BusinessFiltersRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterRequest.getCity())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("businessCity")), //nombre del atributo
                        "%" + filterRequest.getCity().toLowerCase() + "%"
                ));

            }

            if (StringUtils.hasLength(filterRequest.getState())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("businessState")), //nombre del atributo
                        "%" + filterRequest.getState().toLowerCase() + "%"
                ));

            }

            if (StringUtils.hasLength(filterRequest.getCountry())) { // pregunta si tiene algo el filter
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("businessCountry")), //nombre del atributo
                        "%" + filterRequest.getCountry().toLowerCase() + "%"
                ));

            }


            //remueve duplicados
            query.distinct(true);

            // Resuelve el orden
            String orderByField = "timestamp"; //"date = nombre del atributo
            query.orderBy(
                    filterRequest.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and((javax.persistence.criteria.Predicate[])
                    predicates.toArray(new Predicate[0]));
        };
    }



}
