package com.c702t.Cerveza.repository;

import com.c702t.Cerveza.models.entity.SlideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlideRepository extends JpaRepository<SlideEntity, Long> {

}
