package com.jmtsu.ms.auth.endpoint.controller;


import com.jmtsu.ms.auth.dto.AuthRequest;
<<<<<<< HEAD
import com.jmtsu.ms.auth.dto.UserRequest;
import com.jmtsu.ms.auth.endpoint.service.AuthService;
import com.jmtsu.ms.core.model.UserModel;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
=======
import com.jmtsu.ms.auth.endpoint.service.AuthService;
import com.jmtsu.ms.core.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.util.UUID;

=======
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signup")
<<<<<<< HEAD
    public String signup(@RequestBody UserRequest userRequest){

    var userModel = new UserModel();
    BeanUtils.copyProperties(userRequest, userModel);

=======
    public String signup(@RequestBody UserModel userModel){
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf

        return authService.saveUser(userModel);
    }

<<<<<<< HEAD
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
=======
    @PostMapping("/login")
    public String getToken(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()){
            return authService.generateToken(authRequest.getUsername());
>>>>>>> 341f85a972fbfa08f7b4d111b48cf548c69fc9cf
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
