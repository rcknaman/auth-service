package org.example.service;

import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserServiceDetailsImpl implements UserDetailsService {

    @Autowired
    private CustomUserDetails customUserDetails;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var userDetail = userRepository.findByUsername(username);
        if(userDetail.isEmpty()){
            throw new UsernameNotFoundException("user not found!!");
        }
        return new CustomUserDetails(userDetail.get());
    }
}
