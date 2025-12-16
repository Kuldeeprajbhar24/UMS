package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.entity.UserAddress;
import com.usermanagement.usermanagement.entity.UserMaster;
import com.usermanagement.usermanagement.repository.AddressRepository;
import com.usermanagement.usermanagement.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/addresses")
@CrossOrigin
public class AddressController {
    private final AddressRepository addressRepo;
    private final UserRepository userRepo;

    public AddressController(AddressRepository addressRepo, UserRepository userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<UserAddress> getAddresses(@PathVariable Long userId) {
        return addressRepo.findByUserUserId(userId);
    }

    @PostMapping
    public UserAddress addAddress(@PathVariable Long userId, @RequestBody UserAddress address) {
        Optional<UserMaster> userOpt = userRepo.findById(userId);

        if (userOpt.isPresent()) {
            address.setUser(userOpt.get());
            return addressRepo.save(address);
        }

        return null;
    }
    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        addressRepo.deleteById(addressId);
    }
}
