package com.esraa.chatapplication.service;


import com.esraa.chatapplication.dao.UserRepo;
import com.esraa.chatapplication.model.User;
import com.esraa.chatapplication.model.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public User saveUser(User user) {
        return userRepo.save(user);
    }

    public ResponseEntity<?> getProfile(UserDetails userDetails) {
        // Fetch user from database
        User user = userRepo.findByUsername(userDetails.getUsername());
        // Return user profile
        return ResponseEntity.ok(new UserProfileResponse(user.getUsername()));
    }

    public ResponseEntity<?> updateProfile(UserDetails userDetails, String username) {

        User user = userRepo.findByUsername(userDetails.getUsername());
        user.setUsername(username);
        userRepo.save(user);

        return ResponseEntity.ok(new UserProfileResponse(user.getUsername()));

    }
}
