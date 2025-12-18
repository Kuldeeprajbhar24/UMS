package com.usermanagement.usermanagement.controller;

import com.usermanagement.usermanagement.entity.UserAddress;
import com.usermanagement.usermanagement.entity.UserMaster;
import com.usermanagement.usermanagement.repository.AddressRepository;
import com.usermanagement.usermanagement.repository.UserRepository;

import com.usermanagement.usermanagement.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users/{userId}/addresses")
@CrossOrigin
public class AddressController {

    private final AddressService service;

    public AddressController(AddressService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserAddress> getAddresses(@PathVariable Long userId) {
        return service.getAddresses(userId);
    }

    @PostMapping
    public UserAddress addAddress(
            @PathVariable Long userId,
            @RequestBody UserAddress address) {
        return service.addAddress(userId, address);
    }
    @DeleteMapping("/{addressId}")
    public void deleteAddress(@PathVariable Long addressId) {
        service.deleteAddress(addressId);
    }
}
