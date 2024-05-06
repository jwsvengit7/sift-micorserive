package com.sifts.Sifts.Core.Service.web.services;

import com.sifts.Commons.model.request.AppIDRequest;
import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AppUserService {
    ResponseEntity<APIResponse<CoreResponse>>  getUser(Long id, HttpServletRequest request);
    ResponseEntity<APIResponse<CoreResponse>>  createUser(AppUserRequest appUserRequest, HttpServletRequest request);
    ResponseEntity<APIResponse<CoreResponse>>  login(AppIDRequest appIDRequest);

}
