package com.c702t.Cerveza.repository.specification;

import com.c702t.Cerveza.models.entity.NewsEntity;
import com.c702t.Cerveza.models.request.specification.NewsFilterRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsSpecification {
//    public Specification<NewsEntity> getByFilters(NewsFilterRequest filterRequest) {
//        return (root, query, criteriaBuilder) -> {
//
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (filterRequest.getId() != null) {
//                //nombre del atributo del getIdGender o sea idGender
////                Join<GenderEntity, MovieEntity> join = root.join("idGender", JoinType.INNER);
////                Expression<String> genderId = join.get("id");
//                predicates.add(genderId.in(filterRequest.getId()));
//
//            }
//
//            if (StringUtils.hasLength(filterRequest.getState())) { // pregunta si tiene algo el filter
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("businessState")), //nombre del atributo
//                        "%" + filterRequest.getState().toLowerCase() + "%"
//                ));
//
//            }
//
//            if (StringUtils.hasLength(filterRequest.getCountry())) { // pregunta si tiene algo el filter
//                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("businessCountry")), //nombre del atributo
//                        "%" + filterRequest.getCountry().toLowerCase() + "%"
//                ));
//
//            }
//
//
//            //remueve duplicados
//            query.distinct(true);
//
//            // Resuelve el orden
//            String orderByField = "timestamp"; //"date = nombre del atributo
//            query.orderBy(
//                    filterRequest.isASC() ?
//                            criteriaBuilder.asc(root.get(orderByField)) :
//                            criteriaBuilder.desc(root.get(orderByField))
//            );
//
//            return criteriaBuilder.and((javax.persistence.criteria.Predicate[])
//                    predicates.toArray(new Predicate[0]));
//        };
//    }

}
