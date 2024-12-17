package com.jmtsu.ms.auth.endpoint.controller;


import com.jmtsu.ms.auth.dto.AuthRequest;
import com.jmtsu.ms.auth.dto.UserRequest;
import com.jmtsu.ms.auth.endpoint.service.AuthService;
import com.jmtsu.ms.core.model.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public String signup(@RequestBody UserRequest userRequest){

    var userModel = new UserModel();
    BeanUtils.copyProperties(userRequest, userModel);


        return authService.saveUser(userModel);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code ){
        if (authService.verify(UUID.fromString(code))){
            return "verify_sucess";
        }else {
            return "verify_fail";
        }
    }

    @PostMapping("/login")
    public String getToken(@RequestBody @Valid AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getEmail());
        }else{
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        authService.validateToken(token);
        return "token valid";
    }
}
