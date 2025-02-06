package com.example.ABD.Application.Service;

import com.example.ABD.Application.Model.User;
import com.example.ABD.Application.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserPassService {

    private BCryptPasswordEncoder encode=new BCryptPasswordEncoder(12);

    @Autowired
    private UserRepo ur;

    public User feef(User user){

        user.setPassword(encode.encode(user.getPassword()));

        ur.save(user);

        return user;



    }

}
