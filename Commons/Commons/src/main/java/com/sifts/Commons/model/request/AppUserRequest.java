package com.sifts.Commons.model.request;

import com.sifts.Commons.validators.annotation.ValidateAppFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRequest {
    @ValidateAppFields
    private String name;

    @ValidateAppFields
    private String email;

    @ValidateAppFields
    private String password;

    @ValidateAppFields
    private String dob;

    @ValidateAppFields
    private String NIN;

    private String photoUrl;

    @ValidateAppFields
    private String age;

    @ValidateAppFields
    private String firsrtName;

    @ValidateAppFields
    private String lastName;

    private LocalDateTime createdAt;
}
