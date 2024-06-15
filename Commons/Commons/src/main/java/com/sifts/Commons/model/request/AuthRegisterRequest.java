package com.sifts.Commons.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterRequest {
    private String name;
    private String email;
    private String password;
    private String dob;
    private String NIN;
    private String photoUrl;
    private String age;
    private String firsrtName;
    private String lastName;

}
