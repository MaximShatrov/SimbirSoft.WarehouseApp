package com.simbirsoft.shatrov.WarehouseApp.security;

import com.simbirsoft.shatrov.WarehouseApp.entity.User;
import com.simbirsoft.shatrov.WarehouseApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findById(login).orElseThrow(() ->
                new UsernameNotFoundException("User with login: " + login + " not found!"));
        return SecurityUser.fromDBUser(user);
    }
}
