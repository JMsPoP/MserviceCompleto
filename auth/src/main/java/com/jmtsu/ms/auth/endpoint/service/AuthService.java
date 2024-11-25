package com.jmtsu.ms.auth.endpoint.service;

import com.jmtsu.ms.core.model.UserModel;
import com.jmtsu.ms.core.repository.UserModelRepository;
import com.jmtsu.ms.core.repository.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;


    public String saveUser(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModelRepository.save(userModel);
        return "user added to the system";
    }

    public String generateToken(String username){
        return jwtService.generateToken(username);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }
}
