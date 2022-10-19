package com.c702t.Cerveza.repository;

import com.c702t.Cerveza.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * from users WHERE users.email LIKE %:email% AND soft_delete = false", nativeQuery = true)
    Optional<UserEntity> findByEmail(String email);

}
