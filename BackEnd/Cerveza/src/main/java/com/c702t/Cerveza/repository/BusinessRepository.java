package com.c702t.Cerveza.repository;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity,Long>,
                                            JpaSpecificationExecutor<BusinessEntity> {

    @Override
    List<BusinessEntity> findAll(Specification<BusinessEntity> specification);
}
