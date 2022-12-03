package com.example.socialApi.model;

import com.example.socialApi.dto.SignUpDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter
@Entity
public class Users  {

    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;

    @Column(nullable = false,unique = true)
    private String nickname;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Lob
    private String coverPic;

    @Lob
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
