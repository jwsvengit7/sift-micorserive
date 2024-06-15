package com.sifts.Sifts.Auth.Module.web.services;

import com.sifts.Commons.model.request.AuthLoginRequest;
import com.sifts.Commons.model.request.AuthRegisterRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;

public interface AuthService {
    public APIResponse<CoreResponse> loginToBranic(AuthLoginRequest request);
    public CoreResponse createAccountBranic(AuthRegisterRequest request);
}
