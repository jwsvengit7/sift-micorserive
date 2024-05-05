package com.sifts.Sifts.Core.Service.config;



import com.sifts.Sifts.Core.Service.domain.enities.AppID;
import com.sifts.Sifts.Core.Service.domain.repository.AppIDRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppIDRepository appIDRepository;


    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String appName)  {
        AppID appChannel = appIDRepository.findByAppName(appName);
        if(Objects.isNull(appChannel)){
            throw new RuntimeException("AppChannel Not found");
        }
        return new User(appChannel.getAppName(),appChannel.getAppPassword(),getAuthorities(appChannel));
    }

    private Collection<GrantedAuthority> getAuthorities(AppID appChannel){
        return Collections.singletonList(new SimpleGrantedAuthority(appChannel.getAppName()));
    }
}