package com.guess.repository;

import com.guess.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "SELECT * FROM user_ " +
            "WHERE deleted_at IS NULL " +
            "AND email = :email ",
            nativeQuery = true)
    Optional<UserEntity> findOneByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM user_ " +
            "WHERE deleted_at IS NULL " +
            "AND id = :userId ",
            nativeQuery = true)
    Optional<UserEntity> findOneById(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM user_ " +
            "WHERE deleted_at IS NULL " +
            "ORDER BY created_at DESC ",
            countQuery = "SELECT COUNT(id) FROM user_ " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    Page<UserEntity> findAllSortedByCreation(Pageable pageable);

}
