package com.sifts.Sifts.Core.Service.web.controllers;

import com.sifts.Commons.model.request.AppIDRequest;
import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Core.Service.web.services.AppUserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Temple Jack chiorlu
 */

@RequiredArgsConstructor
@RequestMapping("/api/core")
@RestController
public class AppUserControllers {
    private final AppUserService appUserService;

    @GetMapping("get-user/{id}")
    public ResponseEntity<APIResponse<CoreResponse>>  getUserById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest){
        return appUserService.getUser(id,httpServletRequest);
    }
    @PostMapping("create-user")
    public ResponseEntity<APIResponse<CoreResponse>>  createUser(@RequestBody AppUserRequest appUserRequest, HttpServletRequest httpServletRequest){
        return appUserService.createUser(appUserRequest,httpServletRequest);
    }
    @PostMapping("login")
    public ResponseEntity<APIResponse<CoreResponse>>  loginCore(@RequestBody AppIDRequest appIDRequest){
        return appUserService.login(appIDRequest);
    }
}
