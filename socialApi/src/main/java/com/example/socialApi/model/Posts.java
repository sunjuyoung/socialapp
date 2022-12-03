package com.example.socialApi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimes {

    @Id @GeneratedValue
    @Column(name = "posts_id")
    private Long id;

    private String description;
    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    private LocalDateTime createdAt;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "posts")
    private List<Likes> likes = new ArrayList<>();





}
