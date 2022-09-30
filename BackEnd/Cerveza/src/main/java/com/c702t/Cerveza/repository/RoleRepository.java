package com.c702t.Cerveza.repository;

import com.c702t.Cerveza.models.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Set<RoleEntity> findByName(String name);

}