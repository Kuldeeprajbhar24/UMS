package com.usermanagement.usermanagement.repository;

import com.usermanagement.usermanagement.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserMaster, Long> {
}
