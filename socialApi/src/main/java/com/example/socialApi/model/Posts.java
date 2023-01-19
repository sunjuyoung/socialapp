package com.example.socialApi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Posts extends BaseTimes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    private String description;

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;


    public void uploadImg(String img){
        this.img = img;
    }



}
