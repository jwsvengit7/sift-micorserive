package com.sifts.Commons.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {
    private String email;
    private String password;
    private AppDetails appDetails;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppDetails{
        private String appName;
        private String appPassword;
    }
}
