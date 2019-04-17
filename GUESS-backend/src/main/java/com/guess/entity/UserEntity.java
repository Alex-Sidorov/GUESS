package com.guess.entity;

import com.guess.entity.enums.UserRoleEntity;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_")
public class UserEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, columnDefinition = "citext")
    private String email;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Type(type = "enumType")
    @Enumerated(STRING)
    @Column(name = "role")
    private UserRoleEntity role;

}
