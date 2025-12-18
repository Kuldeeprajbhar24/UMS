package com.usermanagement.usermanagement.controller;
import com.usermanagement.usermanagement.dto.UserDto;
import com.usermanagement.usermanagement.entity.UserMaster;
import com.usermanagement.usermanagement.exception.ResourceNotFoundException;
import com.usermanagement.usermanagement.repository.UserRepository;
import com.usermanagement.usermanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserRepository repo;
    private final UserService service;
    private final PasswordEncoder encoder;

    public UserController(UserRepository repo,
                          UserService service,
                          PasswordEncoder encoder) {
        this.repo = repo;
        this.service = service;
        this.encoder = encoder;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserMaster> createUser(@RequestBody UserMaster user) {
        user.setUserPassword(encoder.encode(user.getUserPassword()));
        UserMaster saved = repo.save(user);
        service.clearCache();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public UserMaster updateUser(
            @PathVariable Long id,
            @RequestBody UserMaster newUser) {

        UserMaster user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setUserName(newUser.getUserName());
        user.setUserPhone(newUser.getUserPhone());
        user.setStatus(newUser.getStatus());

        if (newUser.getUserPassword() != null) {
            user.setUserPassword(encoder.encode(newUser.getUserPassword()));
        }

        service.clearCache();
        return repo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        repo.deleteById(id);
        service.clearCache();
    }
}
