package org.example.service;

import org.example.entities.UserInfo;
import org.example.entities.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;


    public CustomUserDetails(UserInfo userInfo){
        this.username=userInfo.getUsername();
        this.password=userInfo.getPassword();
        List<GrantedAuthority> list = new ArrayList<>();
        for(UserRoles r: userInfo.getRoles()){
            list.add(new SimpleGrantedAuthority(r.getName().toUpperCase()));
        }
        this.authorities=list;
    }
    @Override
    public String getUsername(){
        return this.username;
    }
    @Override
    public String getPassword(){
        return this.password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return this.authorities;
    }
}
