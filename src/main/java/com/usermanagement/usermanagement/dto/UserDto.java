package com.usermanagement.usermanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String userName;
    private String userPhone;
    private String status;
    private LocalDateTime dateOfRegistration;
    private List<String> addresses;
}
