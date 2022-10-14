package com.c702t.Cerveza.repository;

import com.c702t.Cerveza.models.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    @Query(value = "SELECT * FROM news WHERE news.business_id = ? AND soft_delete = false", nativeQuery = true)
    List<NewsEntity> findByBusiness_id(Long id);




}
