package com.thevault.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SocialUserInfo {

    private String email;
    private String providerId;
    private String firstName;
    private String lastName;
}
