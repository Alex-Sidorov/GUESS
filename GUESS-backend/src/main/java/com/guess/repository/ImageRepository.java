package com.guess.repository;

import com.guess.entity.ImageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID> {

    @Query(value = "SELECT * FROM image " +
            "WHERE deleted_at IS NULL " +
            "ORDER BY created_at DESC ",
            countQuery = "SELECT COUNT(id) FROM image " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    Page<ImageEntity> findAllSortedByCreation(Pageable pageable);

    @Query(value = "SELECT * FROM image " +
            "WHERE user_id = :userId " +
            "AND deleted_at IS NULL " +
            "ORDER BY created_at DESC ",
            countQuery = "SELECT COUNT(id) FROM image " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<ImageEntity> findAllByUserIdSortedByCreation(@Param("userId") UUID userId,
                                                      Pageable pageable);

    @Query(value = "SELECT * FROM image " +
            "WHERE id = :imageId " +
            "AND deleted_at IS NULL ",
            nativeQuery = true)
    Optional<ImageEntity> findOneById(@Param("imageId") UUID imageId);

    @Modifying
    @Query(value = "UPDATE image " +
            "SET deleted_at = now() " +
            "WHERE id = :imageId " +
            "AND deleted_at IS NULL ",
            nativeQuery = true)
    void softDeleteById(@Param("imageId") UUID imageId);

}
