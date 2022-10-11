package com.c702t.Cerveza.repository;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<BusinessEntity,Long>,
                                            JpaSpecificationExecutor<BusinessEntity> {

    @Override
    List<BusinessEntity> findAll(Specification<BusinessEntity> specification);
   @Override
    Page<BusinessEntity> findAll(@Nullable Specification<BusinessEntity> specification, Pageable var2);
}
