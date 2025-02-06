package com.example.ABD.Application.Service;

import com.example.ABD.Application.Model.User;
import com.example.ABD.Application.Model.UserProvider;
import com.example.ABD.Application.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=ur.findByName(username);

        return new UserProvider(user);
    }
}
