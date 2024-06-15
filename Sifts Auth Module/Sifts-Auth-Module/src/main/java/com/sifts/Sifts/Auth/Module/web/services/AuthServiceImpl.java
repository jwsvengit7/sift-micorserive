package com.sifts.Sifts.Auth.Module.web.services;

import com.google.gson.Gson;
import com.sifts.Commons.jwtservice.JwtService;
import com.sifts.Commons.model.request.AuthLoginRequest;
import com.sifts.Commons.model.request.AuthRegisterRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Auth.Module.domain.enities.User;
import com.sifts.Sifts.Auth.Module.domain.repository.UserRepository;
import com.sifts.Sifts.Auth.Module.helper.Restemplates;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Temple Jack Chiorlu
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final Restemplates dio;
    private final static Logger LOGGER = Logger.getLogger(AuthServiceImpl.class.getName());
    private HttpServletRequest httpServletRequest;
    private Gson gson;

    private  JwtService jwtService;

    private final UserRepository userRepository;

    public AuthServiceImpl(Restemplates dio, HttpServletRequest httpServletRequest, Gson gson, UserRepository userRepository) {
        this.dio = dio;
        this.httpServletRequest = httpServletRequest;
        this.gson = gson;
        this.userRepository = userRepository;
    }


    public APIResponse<CoreResponse> loginToBranic(AuthLoginRequest request){
        CoreResponse response = new CoreResponse();
        Map<String,String> appRequest = new HashMap<>();
        appRequest.put("appName",request.getAppDetails().getAppName());
        appRequest.put("appPassword",request.getAppDetails().getAppPassword());
        response = dio.restTemplate().postForObject("/login",appRequest,CoreResponse.class);
        assert response != null;
        if (response.getStatus().equals("-99")){
            CoreResponse coreResponse = (CoreResponse) response.getData();
            LOGGER.log(Level.FINE," >>>>>>>>>>>>>>> "+gson.toJson(coreResponse)+" <<<<<<<<<<<<<<<<<<<<<<");
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("CORE-TOKEN",coreResponse.getToken());
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword());
            String jwt = jwtService.generateToken(authentication,"");

        }

        return new APIResponse<>();

    }
    public CoreResponse createAccountBranic(AuthRegisterRequest request) {
        CoreResponse coreResponse = new CoreResponse();
        User user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(Objects.nonNull(user)){
            coreResponse.setStatus("-00");
            coreResponse.setResponseMessage("User Already Exist");
        }
        ResponseEntity<?> response = null;
        Map<String, String> appRequest = new HashMap<>();
        appRequest.put("name", request.getName());
        appRequest.put("password", request.getPassword());
        appRequest.put("age", request.getAge());
        appRequest.put("DOB", request.getDob());
        appRequest.put("NIN", request.getNIN());
        appRequest.put("firstName", request.getFirsrtName());
        appRequest.put("url", request.getPhotoUrl());
        response = dio.restTemplate().postForObject("/opeanAccount", appRequest, ResponseEntity.class);
        assert response != null;

        if (!response.getStatusCode().is2xxSuccessful()) {
            coreResponse = (CoreResponse) response.getBody();
            coreResponse.setData(null);
            return coreResponse;
        }

            LOGGER.log(Level.FINE, " >>>>>>>>>>>>>>> " + gson.toJson(coreResponse) + " <<<<<<<<<<<<<<<<<<<<<<");
            User newUser  = new User();
        newUser.setDob(request.getDob());
        newUser.setUrl(request.getPhotoUrl());
        newUser.setEmail(request.getEmail());
        newUser.setUsername(request.getName());
        newUser.setLastName(request.getLastName());
        newUser.setFirstName(request.getFirsrtName());
            userRepository.save(newUser);
            coreResponse.setData("Account Created Successfully");
            coreResponse.setStatus("200");
            return coreResponse;


    }
    private <T> APIResponse<CoreResponse> response(){
        return new APIResponse<>();
    }

}
