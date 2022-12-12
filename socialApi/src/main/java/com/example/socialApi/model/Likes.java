package com.example.socialApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Likes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posts posts;

}
