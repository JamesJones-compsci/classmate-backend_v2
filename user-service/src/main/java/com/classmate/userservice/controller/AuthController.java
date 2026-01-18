package com.classmate.userservice.controller;


import com.classmate.userservice.dto.AuthResponseDTO;
import com.classmate.userservice.dto.LoginRequestDTO;
import com.classmate.userservice.dto.RegisterRequestDTO;
import com.classmate.userservice.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {

        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO request){
        return authService.register(request);
    }


    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request){
        return authService.login(request);
    }
}
