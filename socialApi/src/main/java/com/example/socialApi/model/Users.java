package com.example.socialApi.model;

import com.example.socialApi.dto.ProfileDTO;
import com.example.socialApi.dto.SignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Users  {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String coverPic;

    private String profilePic;

    private String city;

    private String website;

    @OneToMany(mappedBy = "followUser")
    private List<Relationships> followUser = new ArrayList<>();
    @OneToMany(mappedBy = "followedUser")
    private List<Relationships> followedUser = new ArrayList<>();

    private LocalDateTime createdAt;

    public Users(SignUpDTO signUpDTO){
        this.nickname = signUpDTO.getNickname();
        this.email = signUpDTO.getEmail();
        this.password = signUpDTO.getPassword();
        this.profilePic = signUpDTO.getProfilePic();
        this.coverPic = signUpDTO.getCoverPic();
        this.website = signUpDTO.getWebsite();
        this.city = signUpDTO.getCity();
        this.createdAt = LocalDateTime.now();
    }
    public Users(Long id){
        this.id = id;
    }

    public void updateUser(ProfileDTO profileDTO){
        this.nickname = profileDTO.getNickname();
        this.profilePic = profileDTO.getProfilePic();
        this.city = profileDTO.getCity();
        this.website = profileDTO.getWebsite();
        this.coverPic = profileDTO.getCoverPic();
    }


}
