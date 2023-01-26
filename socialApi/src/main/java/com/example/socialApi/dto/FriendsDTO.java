package com.example.socialApi.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendsDTO {

    private Long userId;

    private String nickname;

    private String email;

    private String profilePic;

    private Long friendId;


    @QueryProjection
    public FriendsDTO(Long userId, String nickname, String profilePic, Long friendId) {
        this.userId = userId;
        this.nickname = nickname;
        this.profilePic = profilePic;
        this.friendId = friendId;
    }
}
