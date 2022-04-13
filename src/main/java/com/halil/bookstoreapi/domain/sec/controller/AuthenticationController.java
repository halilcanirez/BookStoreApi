package com.halil.bookstoreapi.domain.sec.controller;

import com.halil.bookstoreapi.domain.sec.dto.SecLoginRequestDto;
import com.halil.bookstoreapi.domain.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public String login(@RequestBody SecLoginRequestDto secLoginRequestDto){
        String token = authenticationService.login(secLoginRequestDto);
        return token;
    }

}
