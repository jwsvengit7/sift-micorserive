package com.sifts.Sifts.Core.Service.web.controllers;

import com.sifts.Commons.model.request.AppIDRequest;
import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Core.Service.web.services.CoreService;
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
    private final CoreService coreService;

    @GetMapping("/get-user/{id}")
    public APIResponse<CoreResponse>  getUserById(@PathVariable("id") Long id, HttpServletRequest httpServletRequest){
        return coreService.getUser(id,httpServletRequest);
    }
    @PostMapping("/create-user")
    public APIResponse<CoreResponse>  createUser(@RequestBody AppUserRequest appUserRequest, HttpServletRequest httpServletRequest){
        return coreService.createUser(appUserRequest,httpServletRequest);
    }
    @PostMapping("/login")
    public APIResponse<CoreResponse>  loginCore(@RequestBody AppIDRequest appIDRequest){
        return coreService.login(appIDRequest);
    }
}
