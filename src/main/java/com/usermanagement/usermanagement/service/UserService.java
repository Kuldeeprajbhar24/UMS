package com.usermanagement.usermanagement.service;
import com.usermanagement.usermanagement.dto.UserDto;
import com.usermanagement.usermanagement.entity.UserAddress;
import com.usermanagement.usermanagement.entity.UserMaster;
import com.usermanagement.usermanagement.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    @Cacheable("users")
    public List<UserDto> getAllUsers() {

        List<UserMaster> users = repo.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for (UserMaster user : users) {

            List<String> addresses = new ArrayList<>();
            for (UserAddress a : user.getAddresses()) {
                addresses.add(a.getFullAddress());
            }

            UserDto dto = UserDto.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userPhone(user.getUserPhone())
                    .dateOfRegistration(user.getDateOfRegistration())
                    .status(user.getStatus())
                    .addresses(addresses)
                    .build();

            dtoList.add(dto);
        }
        return dtoList;
    }
    @CacheEvict(value = "users", allEntries = true)
    public void clearCache() {
    }
}
