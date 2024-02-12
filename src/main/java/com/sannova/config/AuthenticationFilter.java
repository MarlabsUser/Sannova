package com.sannova.config;

import com.sannova.exceptionhandler.SannovaExceptionHandler;
import com.sannova.model.User;
import com.sannova.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;

import static com.sannova.util.MessageConstant.INVALID_LOGIN_CRIDENTIAL_MESSAGE;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String userName=request.getHeader("username");
        String password=request.getHeader("password");

        Optional<User> userDetails=userRepository.findByUserName(userName);
        if(!userDetails.isPresent()){
            throw SannovaExceptionHandler.builder().message(INVALID_LOGIN_CRIDENTIAL_MESSAGE).build();
        }

        String decodePassword= new String(Base64.getDecoder().decode(userDetails.get().getPassword().getBytes()));
        if(!StringUtils.equals(password,decodePassword)){
            throw SannovaExceptionHandler.builder().message(INVALID_LOGIN_CRIDENTIAL_MESSAGE).build();
        }
        filterChain.doFilter(request, response);
    }

}
