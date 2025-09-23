package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entities.UserInfo;
import org.example.eventProducer.Producer;
import org.example.models.UserInfoDto;
import org.example.repository.UserRepository;
import org.example.utils.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class UserServiceDetailsImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private Producer kafkaProducer;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        var userDetail = userRepository.findByUsername(username);
        if(userDetail.isEmpty()){
            throw new UsernameNotFoundException("user not found!!");
        }
        return new CustomUserDetails(userDetail.get());
    }

    public Optional<UserInfo> checkIfUserAlreadyExist(UserInfoDto userInfoDto){
        return userRepository.findByUsername(userInfoDto.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto){
        Boolean isValid = Validation.validateUserAttributes(userInfoDto);
        if(!isValid){
            throw new IllegalArgumentException("please pass valid arguments");
        }
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if(checkIfUserAlreadyExist(userInfoDto).isPresent()){
            return false;
        }
        String userId = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(userId, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        kafkaProducer.sendMessage(topicName, userInfoDto);
        return true;
    }
}
