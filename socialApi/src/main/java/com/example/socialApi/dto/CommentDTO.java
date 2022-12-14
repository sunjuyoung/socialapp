package com.example.socialApi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private Long userId;
    private String nickname;
    private String description;
    private String profilePic;
    private LocalDateTime modifiedBy;
}
