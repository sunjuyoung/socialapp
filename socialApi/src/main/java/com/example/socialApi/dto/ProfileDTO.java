package com.example.socialApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO implements Serializable {

    private Long id;

    private String nickname;

    private String email;

    private String coverPic;

    private String profilePic;

    private String city;

    private String website;
}
