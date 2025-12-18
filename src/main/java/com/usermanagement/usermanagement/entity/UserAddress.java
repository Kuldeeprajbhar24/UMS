package com.usermanagement.usermanagement.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserMaster user;

    @Column(nullable = false)
    private String fullAddress;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        if (createdAt == null)
            createdAt = LocalDateTime.now();
    }
}
