package com.jmtsu.ms.auth.config;

import com.jmtsu.ms.core.model.UserModel;
import com.jmtsu.ms.core.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserModelRepository userModelRepository;
    @Override
<<<<<<< HEAD
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional< UserModel > userModel = userModelRepository.findByEmail(email);
        return userModel.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found with name:" + email));
=======
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional< UserModel > userModel = userModelRepository.findByUsername(username);
        return userModel.map(CustomUserDetails::new).orElseThrow(()-> new UsernameNotFoundException("user not found with name:" + username));
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
    }
}
