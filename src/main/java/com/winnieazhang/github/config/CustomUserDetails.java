package com.winnieazhang.github.config;

import com.winnieazhang.github.entity.Role;
import com.winnieazhang.github.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//oAuth2 explanation: http://www.mamicode.com/info-detail-1550047.html

/**
 * Created by Wayne.A.Z on 2018/7/20.
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User byUsername) {
        this.username = byUsername.getUsername();
        this.password=byUsername.getPassword();

        List<GrantedAuthority> auths =new ArrayList<>();

        for(Role role:byUsername.getRoles())
            auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));

        this.authorities=auths;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
