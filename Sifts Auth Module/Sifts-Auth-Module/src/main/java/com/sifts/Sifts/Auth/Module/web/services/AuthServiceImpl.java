package com.sifts.Sifts.Auth.Module.web.services;

import com.sifts.Commons.model.request.AuthLoginRequest;
import com.sifts.Commons.model.request.AuthRegisterRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Auth.Module.helper.Restemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Temple Jack Chiorlu
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private Restemplates dio;


    public APIResponse<CoreResponse> loginToBranic(AuthLoginRequest request){
        return null;

    }
    public APIResponse<CoreResponse> createAccountBranic(AuthRegisterRequest request){
        dio.restTemplate().postForEntity("create-user",request,Object.class);
        return new APIResponse<>(new CoreResponse());
    }
    private APIResponse<CoreResponse> response(){
        return new APIResponse<>();
    }

}
