package com.example.socialApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Comments extends BaseTimes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comments_id")
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posts posts;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;
}
