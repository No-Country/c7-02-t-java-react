package com.c702t.Cerveza.repository.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import com.c702t.Cerveza.models.request.specification.BusinessByUserRequest;
import com.c702t.Cerveza.models.request.specification.BusinessFiltersRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class BusinessByUserSpecification {

    public Specification<BusinessEntity> getByUsers(BusinessByUserRequest filterRequest) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterRequest.getIdUser() != null) {
                Join< BusinessEntity, UserEntity> join = root.join("users", JoinType.INNER);//nombre del atributo
                Expression<String> idUser = join.get("id"); //nombre de la columna
                predicates.add(idUser.in(filterRequest.getIdUser()));
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
