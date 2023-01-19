package com.example.socialApi.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostDTO {

    private Long postId;

    private Long userId;

    private String profilePic;

    private String description;

    private String img;

    private int likeCount;

    private String nickname;

    private LocalDateTime modifiedBy;

    private int commentCount;


    public PostDTO(Long postId, String description,String nickname,
                  Long userId,String profilePic,LocalDateTime modifiedBy) {
        this.postId = postId;
        this.description = description;
        this.nickname = nickname;
        this.userId = userId;
        this.profilePic = profilePic;
        this.modifiedBy = modifiedBy;
    }


    @QueryProjection
    public PostDTO(Long postId, String description, int likeCount, String nickname,
                   LocalDateTime modifiedBy,Long userId,String img,String profilePic,int commentCount) {
        this.postId = postId;
        this.description = description;
        this.likeCount = likeCount;
        this.nickname = nickname;
        this.modifiedBy = modifiedBy;
        this.userId = userId;
        this.img = img;
        this.profilePic = profilePic;
        this.commentCount = commentCount;

    }
}
