package com.sannova.service;

import com.sannova.dto.LoginRequest;
import com.sannova.dto.LoginResponse;
import com.sannova.exceptionhandler.SannovaExceptionHandler;
import com.sannova.model.User;
import com.sannova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

import static com.sannova.util.MessageConstant.*;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<User> userDetails=userRepository.findByUserName(request.getFirstname());
        if(!userDetails.isPresent()){
            throw SannovaExceptionHandler.builder()
                    .message("User details not found").status(HttpStatus.NOT_FOUND).build();
        }
        return LoginResponse.builder()
                .userName(userDetails.get().getUserName())
                .roleId(userDetails.get().getRole().getId())
                .roleName(userDetails.get().getRole().getRoleName())
                .authorization(userDetails.get().getRole().getAuthorization())
                .build();
    }
}
