package com.c702t.Cerveza.repository;
import com.c702t.Cerveza.models.entity.BusinessEntity;
import com.c702t.Cerveza.models.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
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

    @Query(value = "SELECT * from business WHERE business.name LIKE %:name% AND business_address LIKE %:address% AND soft_delete = false", nativeQuery = true)
    Optional<BusinessEntity> findByName(String name, String address);

  //  BusinessEntity findAllByNameAndBusinessAddress(String name, String address);

    @Query(value = "SELECT * from business WHERE business.email LIKE %:email% AND soft_delete = false", nativeQuery = true)
    Optional<BusinessEntity> findByEmail(String email);

}
