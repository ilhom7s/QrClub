package com.project.qrclub.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_uuid")
@NoArgsConstructor
@Getter
@Setter
public class UsersUUID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "user_uuid")
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
