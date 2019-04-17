package com.guess.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class ImageEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false, insertable = false)
    private UUID id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "user_id", updatable = false, insertable = false, nullable = false)
    private UUID userId;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "url", nullable = false)
    private String url;

}
