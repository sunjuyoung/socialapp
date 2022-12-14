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
    private Long postsPhotoId;

    private String description;

    private String img;

    private int likeCount;

    private String nickname;

    private LocalDateTime modifiedBy;




    @QueryProjection
    public PostDTO(Long postId, String description, int likeCount, String nickname,
                   LocalDateTime modifiedBy,Long userId,String img) {
        this.postId = postId;
        this.description = description;
        this.likeCount = likeCount;
        this.nickname = nickname;
        this.modifiedBy = modifiedBy;
        this.userId = userId;
        this.img = img;

    }
}
