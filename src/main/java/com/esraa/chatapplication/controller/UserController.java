package com.esraa.chatapplication.controller;


import com.esraa.chatapplication.model.User;
import com.esraa.chatapplication.service.JwtService;
import com.esraa.chatapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationConfiguration authenticationConfiguration;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping("register")
    public User register(@RequestBody User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }



    @PostMapping("login")
    public String login(@RequestBody User user){

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }

        return "Failure";


    }

    @GetMapping("profile")
    public ResponseEntity<?> profile(@AuthenticationPrincipal UserDetails userDetails){
        return userService.getProfile(userDetails);
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody String username){
        return userService.updateProfile(userDetails , username);
    }

}
