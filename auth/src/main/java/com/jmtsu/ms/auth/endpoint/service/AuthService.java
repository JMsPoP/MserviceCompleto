package com.jmtsu.ms.auth.endpoint.service;

import com.jmtsu.ms.auth.config.RabbitMQ.UserProducer;
import com.jmtsu.ms.core.model.UserModel;
import com.jmtsu.ms.core.repository.UserModelRepository;
import com.jmtsu.ms.core.repository.UserModelRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserModelRepository userModelRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserProducer userProducer;

    @Bulkhead(name = "UserSignUp")
    public String saveUser(UserModel userModel){
        if(userModelRepository.findByEmail(userModel.getEmail()).isPresent()){
            throw new RuntimeException("este email já está cadastrado");
        }else {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        userModel.setVerificationCode(UUID.randomUUID());
        userModel.setEnable(false);
        userProducer.publishMessageEmail(userModel);
        userModelRepository.save(userModel);
        return "user added to the system";}
    }

    public boolean verify(UUID verificationCode){
        UserModel userModel = userModelRepository.findByVerificationCode(verificationCode);

        if(userModel == null || userModel.getEnable() == true){
            return false;
        } else {
            userModel.setVerificationCode(null);
            userModel.setEnable(true);
            userModelRepository.save(userModel);

            return true;
        }
    }

    public String generateToken(String email){
        return jwtService.generateToken(email);
    }

    public void validateToken(String token){
        jwtService.validateToken(token);
    }

}
