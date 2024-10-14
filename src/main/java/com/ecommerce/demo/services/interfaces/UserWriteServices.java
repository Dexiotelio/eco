package com.ecommerce.demo.services.interfaces;

import com.ecommerce.demo.dto.request.RegisterRequest;
import com.ecommerce.demo.dto.request.UserRequest;
import com.ecommerce.demo.dto.response.UserResponse;
import com.ecommerce.demo.util.Result;

public interface UserWriteServices {
    public Result<UserResponse> registerUser(RegisterRequest request);
    public Result<UserResponse> update(UserRequest request);
    public Result<UserResponse>  delete(UserRequest request);
}
