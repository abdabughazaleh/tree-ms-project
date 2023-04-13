package com.treeproject.identity.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "token")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @Column(name = "token_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
