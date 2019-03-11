package com.guess.respository;

import com.guess.entity.PictureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, UUID> {

    @Query(value = "SELECT * FROM picture " +
            "WHERE deleted_at IS NULL " +
            "ORDER BY created_at DESC ",
            countQuery = "SELECT COUNT(id) FROM picture " +
                    "WHERE deleted_at IS NULL ",
            nativeQuery = true)
    Page<PictureEntity> findAllSortedByCreation(Pageable pageable);

    @Query(value = "SELECT * FROM picture " +
            "WHERE user_id = :userId " +
            "AND deleted_at IS NULL " +
            "ORDER BY created_at DESC ",
            countQuery = "SELECT COUNT(id) FROM picture " +
                    "WHERE user_id = :userId " +
                    "AND deleted_at IS NULL ",
            nativeQuery = true)
    Page<PictureEntity> findAllByUserIdSortedByCreation(@Param("userId") UUID userId,
                                                        Pageable pageable);

    @Modifying
    @Query(value = "UPDATE picture " +
            "SET deleted_at = now() " +
            "WHERE id = :pictureId " +
            "AND deleted_at IS NULL ",
            nativeQuery = true)
    void softDeleteById(@Param("pictureId") UUID pictureId);

}
