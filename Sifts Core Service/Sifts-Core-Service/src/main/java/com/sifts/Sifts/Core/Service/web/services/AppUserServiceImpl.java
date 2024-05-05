package com.sifts.Sifts.Core.Service.web.services;

import com.sifts.Commons.model.request.AppIDRequest;
import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.response.CoreResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Temple Jack chiorlu
 */
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl  implements AppUserService {

    @Override
    public ResponseEntity<CoreResponse> getUser(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<CoreResponse> createUser(AppUserRequest appUserRequest, HttpServletRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<CoreResponse> login(AppIDRequest appIDRequest, HttpServletRequest request) {
        return null;
    }
}
