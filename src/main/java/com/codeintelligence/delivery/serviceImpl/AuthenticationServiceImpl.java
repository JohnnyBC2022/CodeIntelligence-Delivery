package com.codeintelligence.delivery.serviceImpl;

import com.codeintelligence.delivery.model.authentication.AuthenticationResponse;
import com.codeintelligence.delivery.model.token.TokenEntity;
import com.codeintelligence.delivery.model.user.UserEntity;
import com.codeintelligence.delivery.repository.TokenRepository;
import com.codeintelligence.delivery.repository.UserRepository;
import com.codeintelligence.delivery.service.AuthenticationService;
import com.codeintelligence.delivery.service.JWTService;
import com.codeintelligence.delivery.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JWTService jwtService;

    private final TokenRepository tokenRepository;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService, TokenRepository tokenRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse register(@NonNull UserEntity request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist");
        }

        UserEntity user = new UserEntity();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMail(request.getMail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        user = userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User registration successfully");
    }

    @Override
    public AuthenticationResponse authenticate(@NonNull UserEntity request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserEntity user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokensByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt, "User login successfully");
    }

    @Override
    public void revokeAllTokensByUser(@NonNull UserEntity user) {
        List<TokenEntity> validTokens = tokenRepository.findAllTokensByUser(user);

        if(validTokens.isEmpty()){
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    @Override
    public void saveUserToken(String jwt, UserEntity user) {
        TokenEntity token = new TokenEntity();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

    @Override
    public AuthenticationResponse signOut(UserEntity signoutRequest) {
        AuthenticationResponse response;
        UserEntity userLeaving = this.userService.getCurrentUser();

        try {
            this.revokeAllTokensByUser(userLeaving);
            response = new AuthenticationResponse(null, "Signed Out Successfully");
        } catch (Exception e) {
            response = new AuthenticationResponse(null, e.getMessage());
        }
        return response;
    }
}
