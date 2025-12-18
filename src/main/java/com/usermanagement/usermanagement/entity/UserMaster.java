package com.usermanagement.usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_master")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userPassword;

    private String userPhone;
    private String status;
    private LocalDateTime dateOfRegistration;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAddress> addresses = new ArrayList<>();

    @PrePersist
    public void onCreate() {
        if (dateOfRegistration == null)
            dateOfRegistration = LocalDateTime.now();
        if (status == null)
            status = "ACTIVE";
    }
}
