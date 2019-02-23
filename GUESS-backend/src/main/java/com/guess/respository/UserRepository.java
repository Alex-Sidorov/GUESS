package com.guess.respository;

import com.guess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query(value = "SELECT * FROM guess_user " +
            "WHERE deleted_at IS NULL " +
            "AND email = :email ",
            nativeQuery = true)
    Optional<UserEntity> findOneByEmail(@Param("email") String email);

}
