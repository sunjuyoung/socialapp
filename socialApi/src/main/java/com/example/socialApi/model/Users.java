package com.example.socialApi.model;

import com.example.socialApi.dto.SignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@NoArgsConstructor @AllArgsConstructor
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


    public Users(SignUpDTO signUpDTO){
        this.nickname = signUpDTO.getNickname();
        this.email = signUpDTO.getEmail();
        this.password = signUpDTO.getPassword();
    }


}
