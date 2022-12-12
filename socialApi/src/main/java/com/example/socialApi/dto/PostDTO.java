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

    private String description;

    private String img;

    private int likeCount;

    private String nickname;

    private LocalDateTime modifiedBy;

    private Long userId;


    @QueryProjection
    public PostDTO(Long postId, String description, String img, int likeCount, String nickname,
                   LocalDateTime modifiedBy,Long userId) {
        this.postId = postId;
        this.description = description;
        this.img = img;
        this.likeCount = likeCount;
        this.nickname = nickname;
        this.modifiedBy = modifiedBy;
        this.userId = userId;
    }
}
