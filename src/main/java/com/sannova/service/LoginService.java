package com.sannova.service;

import com.sannova.dto.LoginRequest;
import com.sannova.dto.LoginResponse;
import org.springframework.stereotype.Service;

public interface LoginService {

    LoginResponse login(LoginRequest request);

}
