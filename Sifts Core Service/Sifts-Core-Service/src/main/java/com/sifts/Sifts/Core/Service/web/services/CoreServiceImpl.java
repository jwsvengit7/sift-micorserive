package com.sifts.Sifts.Core.Service.web.services;

import com.sifts.Commons.model.request.AppIDRequest;
import com.sifts.Commons.model.request.AppUserRequest;
import com.sifts.Commons.model.response.APIResponse;
import com.sifts.Commons.model.response.CoreResponse;
import com.sifts.Sifts.Core.Service.domain.enities.AppID;
import com.sifts.Sifts.Core.Service.domain.enities.AppUser;
import com.sifts.Sifts.Core.Service.domain.repository.AppIDRepository;
import com.sifts.Sifts.Core.Service.domain.repository.AppUserRepository;
import com.sifts.Sifts.Core.Service.security.JWTService;
import com.sifts.Sifts.Core.Service.utils.HeadersUtils;
import com.sifts.Sifts.Core.Service.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author Temple Jack chiorlu
 */
@Service
@RequiredArgsConstructor
public class CoreServiceImpl  implements CoreService {
    private static final Logger LOGGER = Logger.getLogger(CoreServiceImpl.class.getName());

    private final AppIDRepository appIDRepository;
    private final AppUserRepository appUserRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public APIResponse<CoreResponse>  getUser(Long id, HttpServletRequest request) {
        return null;
    }

    @Override
    public APIResponse<CoreResponse>  createUser(AppUserRequest appUserRequest, HttpServletRequest request) {
        boolean validateRequest = HeadersUtils.verifyHeaders(request);
        if(!validateRequest) {
            return mappResponse("UNABLE TO ACCESS HOST ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
            if (Objects.isNull(appUserRequest)) {
                return mappResponse("appUserRequest is null", HttpStatus.BAD_REQUEST);
            }
            AppID appChannel = appIDRepository.findByAppName(SecurityUtils.getLoginUser());
            LOGGER.info(">>>>>>> APP CHANNEL <<<<<<<<<<<<{}" + appChannel);
            if (Objects.isNull(appChannel)) {
                return mappResponse("You need to Verify Your Channel", HttpStatus.BAD_REQUEST);
            }
            AppUser appUser = appUserRepository.findByEmail(appUserRequest.getEmail());
            if (Objects.nonNull(appUser) || appUserRepository.existsByEmail(appUserRequest.getEmail())) {
                return mappResponse("User Already Exist", HttpStatus.CONFLICT);
            }
            AppUser newUser = new AppUser();
            newUser.setAge(appUserRequest.getAge());
            newUser.setDob(appUserRequest.getDob());
            newUser.setEmail(appUserRequest.getEmail());
            newUser.setName(appUserRequest.getName());
            newUser.setNIN(appUserRequest.getNIN());
            newUser.setFirsrtName(appUserRequest.getFirsrtName());
            newUser.setLastName(appUserRequest.getLastName());
            newUser.setPhotoUrl(appUserRequest.getPhotoUrl());
            newUser.setPassword(passwordEncoder.encode(appUserRequest.getPassword()));
            appUserRepository.save(newUser);
            return mappResponse("SIFTS USER " + newUser.getName() + " CREATED", HttpStatus.CREATED);
    }

    @Override
    public APIResponse<CoreResponse> login(AppIDRequest appIDRequest) {

        if(Objects.isNull(appIDRequest)) {
            return mappResponse("AppIDRequest is null",HttpStatus.BAD_REQUEST);
        }
            AppID appChannel = appIDRepository.findByAppNameAndAppPassword(appIDRequest.getAppName(),appIDRequest.getAppPassword());
        LOGGER.info(">>>>>>> APP CHANNEL <<<<<<<<<<<< "+ appChannel);
            if(Objects.isNull(appChannel)){
                return mappResponse("Channel is null",HttpStatus.BAD_REQUEST);
            }
            Authentication authentication = new UsernamePasswordAuthenticationToken(appIDRequest.getAppName(),appIDRequest.getAppPassword());
            String jwtToken = jwtService.generateToken(authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return mappResponse(jwtToken,HttpStatus.OK);
        }

    private <T> APIResponse<CoreResponse> mappResponse(T data,HttpStatus status){
        CoreResponse coreResponse = new CoreResponse();
        APIResponse<CoreResponse> core = new APIResponse<>();
        coreResponse.setData(data);
        core.setData(coreResponse);
        core.setStatus(status.value());
        core.setMessage("Response Recieved");
        return core;
    }

}
