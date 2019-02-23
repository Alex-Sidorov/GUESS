package com.guess.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "picture")
public class PictureEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "user_id", updatable = false, insertable = false, nullable = false)
    private UUID userId;

    @Column(name = "url", nullable = false)
    private String url;

}
