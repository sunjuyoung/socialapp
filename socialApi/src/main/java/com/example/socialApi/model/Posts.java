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

/*    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_photo_id")
    private PostsPhoto postsPhoto;*/

    private String img;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;


/*    @OneToMany(cascade = CascadeType.REMOVE
            ,mappedBy = "posts"
            ,orphanRemoval = true)
    private List<Likes> likes = new ArrayList<>();*/





    public void uploadImg(String img){
        this.img = img;
    }


}
