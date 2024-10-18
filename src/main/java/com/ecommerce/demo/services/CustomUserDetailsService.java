package com.ecommerce.demo.services;

import com.ecommerce.demo.model.MainUserDetails;
import com.ecommerce.demo.repositories.UserWriteRepositoryImpl;
import com.ecommerce.demo.repositories.interfaces.UserQueryRepository;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserWriteRepositoryImpl userWriteRepository;
    private final UserQueryRepository userQueryRepository;

    @Autowired
    public CustomUserDetailsService(UserWriteRepositoryImpl userWriteRepository,
                                    UserQueryRepository userQueryRepository) {
        this.userWriteRepository = userWriteRepository;
        this.userQueryRepository = userQueryRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userQueryRepository.findByUsername(username)
                .flatMap(result -> {
                    if (result.isSuccess()) {
                        return Try.success(new MainUserDetails(result.getValue()));
                    } else {
                        return Try.failure(new UsernameNotFoundException(result.getError()));
                    }
                })
                .recover(e -> {
                    if (e instanceof UsernameNotFoundException) {
                        throw (UsernameNotFoundException) e;
                    }
                    throw new RuntimeException("Unexpected error: " + e.getMessage());
                })
                .get();
    }
}
