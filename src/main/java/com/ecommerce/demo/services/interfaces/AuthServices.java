package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.LoginRequest;
import com.ecommerce.demo.dto.request.RegisterRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.UserResponse;
import io.vavr.control.Either;

public interface AuthServices {
    Either<String, String> authenticate(LoginRequest request);
    //Either<String, String> refreshToken(TokenRefreshRequest request);
    void logout(String token);
    Either<String, UserResponse> register(RegisterRequest request);
    boolean validateToken(String token);
    //UserDetails getUserDetails(String token);
}
