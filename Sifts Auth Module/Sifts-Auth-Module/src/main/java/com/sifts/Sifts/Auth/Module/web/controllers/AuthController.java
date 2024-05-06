package com.sifts.Sifts.Auth.Module.web.controllers;

import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.request.AuthLoginRequest;
import com.sifts.Commons.model.request.AuthRegisterRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Auth.Module.web.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/openAccount")
    public APIResponse<CoreResponse> openAccount(@RequestBody AuthRegisterRequest appUserRequest){
        return authService.createAccountBranic(appUserRequest);
    }

    @PostMapping("/login")
    public APIResponse<CoreResponse> login(@RequestBody AuthLoginRequest authLoginRequest){
        return authService.loginToBranic(authLoginRequest);
    }
}
