package com.esraa.chatapplication.dao;


import com.esraa.chatapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

     User findByUsername(String username);

}
