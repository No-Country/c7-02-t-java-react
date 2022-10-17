package com.c702t.Cerveza.repository;

import com.c702t.Cerveza.models.entity.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlideRepository extends JpaRepository<SlideEntity, Long> {

    @Query(value = "SELECT * FROM slides WHERE slides.business_id = ? AND soft_delete = false", nativeQuery = true)
    List<SlideEntity> findByBusiness_id(Long id);

    public List<SlideEntity> findAllByBusiness_id(Long id);
}
