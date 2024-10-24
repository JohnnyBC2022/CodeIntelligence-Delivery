package com.codeintelligence.delivery.controller;

import com.codeintelligence.delivery.model.authentication.AuthenticationResponse;
import com.codeintelligence.delivery.model.user.UserEntity;
import com.codeintelligence.delivery.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public  AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserEntity request) {
        return  ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserEntity request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/user/signout")
    public ResponseEntity<AuthenticationResponse> signOut(@RequestBody UserEntity signOutRequest){
        return ResponseEntity.ok(authService.signOut(signOutRequest));
    }
}
