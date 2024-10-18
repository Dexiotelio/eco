package com.ecommerce.demo.services;


import com.ecommerce.demo.dto.request.LoginRequest;
import com.ecommerce.demo.dto.request.RegisterRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.jwt.JwtTokenServices;
import com.ecommerce.demo.repositories.UserWriteRepositoryImpl;
import com.ecommerce.demo.repositories.interfaces.UserQueryRepository;
import com.ecommerce.demo.services.interfaces.AuthServices;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServicesImpl implements AuthServices  {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenServices jwtTokenServices;
    private final UserWriteRepositoryImpl userWriteRepository;
    private final UserQueryRepository userQueryRepository;

    @Autowired
    public AuthServicesImpl(UserWriteRepositoryImpl userWriteRepository,
                            UserQueryRepository userQueryRepository,
                            AuthenticationManager authenticationManager,
                            JwtTokenServices jwtTokenServices) {
        this.userWriteRepository = userWriteRepository;
        this.userQueryRepository = userQueryRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
    }

    @Override
    public Either<String, String> authenticate(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            return Either.right(jwtTokenServices.generateToken(request));
        }
        return null;
    }

    @Override
    public void logout(String token) {

    }

    @Override
    public Either<String, UserResponse> register(RegisterRequest request) {
        return null;
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }
}
