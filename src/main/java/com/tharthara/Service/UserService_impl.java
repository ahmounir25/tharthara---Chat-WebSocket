package com.tharthara.Service;

import com.tharthara.Entity.User;
import com.tharthara.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService_impl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService_impl() {
    }

    @Autowired
    public UserService_impl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new RuntimeException("User Already Exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setOnline(true);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void setOnline(String username) {
        User myUser = userRepository.findByUserName(username).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        myUser.setOnline(true);
        userRepository.save(myUser);
    }

    @Override
    public void setOffline(String username) {
        User myUser = userRepository.findByUserName(username).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        myUser.setOnline(false);
        userRepository.save(myUser);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }
}
