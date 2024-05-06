package com.sifts.Sifts.Core.Service.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getLoginUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
