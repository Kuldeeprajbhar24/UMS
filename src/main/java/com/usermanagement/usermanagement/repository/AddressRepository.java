package com.usermanagement.usermanagement.repository;
import com.usermanagement.usermanagement.entity.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findByUserUserId(Long userId);
}
