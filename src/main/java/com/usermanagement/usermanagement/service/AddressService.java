package com.usermanagement.usermanagement.service;

import com.usermanagement.usermanagement.entity.UserAddress;
import com.usermanagement.usermanagement.entity.UserMaster;
import com.usermanagement.usermanagement.exception.ResourceNotFoundException;
import com.usermanagement.usermanagement.repository.AddressRepository;
import com.usermanagement.usermanagement.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepo;
    private final UserRepository userRepo;

    public AddressService(AddressRepository addressRepo, UserRepository userRepo) {
        this.addressRepo = addressRepo;
        this.userRepo = userRepo;
    }

    public List<UserAddress> getAddresses(Long userId) {
        return addressRepo.findByUserUserId(userId);
    }

    public UserAddress addAddress(Long userId, UserAddress address) {
        UserMaster user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        address.setUser(user);
        return addressRepo.save(address);
    }

    public void deleteAddress(Long addressId) {
        addressRepo.deleteById(addressId);
    }
}

