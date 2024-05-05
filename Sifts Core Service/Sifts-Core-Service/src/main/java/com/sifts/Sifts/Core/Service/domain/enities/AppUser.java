package com.sifts.Sifts.Core.Service.domain.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author Temple Jack chiorlu
 */
@Entity
@Table(name = "USER_TB")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUser extends BaseEntity{
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
