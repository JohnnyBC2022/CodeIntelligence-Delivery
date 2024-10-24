package com.codeintelligence.delivery.service;

import com.codeintelligence.delivery.model.authentication.AuthenticationResponse;
import com.codeintelligence.delivery.model.user.UserEntity;

public interface AuthenticationService {

    public AuthenticationResponse register(UserEntity request);

    public AuthenticationResponse authenticate(UserEntity request);

    public void revokeAllTokensByUser(UserEntity user);

    public void saveUserToken(String jwt, UserEntity user);

    public AuthenticationResponse signOut(UserEntity signoutRequest);
}
