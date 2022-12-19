package com.example.socialApi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class SignUpDTO {

    private String nickname;
    private String email;
    private String password;
    private String coverPic;
    private String profilePic;
    private String city;
    private String website;
}
