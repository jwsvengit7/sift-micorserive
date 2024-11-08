package com.sifts.Commons.model.request;

import com.sifts.Commons.validators.annotation.ValidateAppFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppIDRequest {
    @ValidateAppFields
    private String appName;
    @ValidateAppFields
    private String appPassword;

}
