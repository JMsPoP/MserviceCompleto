package com.jmtsu.ms.auth.config;

import com.jmtsu.ms.core.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

<<<<<<< HEAD
    private String email;
    private String password;
    private Boolean enable;

    public CustomUserDetails(UserModel userModel) {
        this.email = userModel.getEmail();
        this.password = userModel.getPassword();
        this.enable = userModel.getEnable();
=======
    private String username;
    private String password;

    public CustomUserDetails(UserModel userModel) {
        this.username = userModel.getUsername();
        this.password = userModel.getPassword();
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
<<<<<<< HEAD
        return email;
=======
        return username;
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
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
<<<<<<< HEAD
        return this.enable;
=======
        return true;
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
    }
}