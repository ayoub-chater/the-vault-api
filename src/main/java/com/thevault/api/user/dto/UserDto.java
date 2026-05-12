package com.thevault.api.user.dto;

import com.thevault.api.user.enums.Gender;
import com.thevault.api.user.enums.ProfileType;
import com.thevault.api.user.enums.Role;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String phoneCountryCode;
    private String country;
    private LocalDate dateOfBirth;
    private Gender gender;
    private Role role;
    private boolean emailVerified;
    private ProfileType profileType;
    private String profilePictureUrl;
    private Integer keyBalance;
}
