package com.arobs.manager;

import com.arobs.domain.User;
import com.arobs.repository.UserRepository;

public class UserManager {
    private UserRepository userRepository=new UserRepository();

    public User getUserByCredentials(String username, String password){
        return userRepository.findUserByCredentials(username,password);
    }
}
