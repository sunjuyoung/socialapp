package com.example.socialApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {

    private Long id;

    private String nickname;

    private String email;

    private String coverPic;

    private String profilePic;

    private String city;

    private String website;
}
