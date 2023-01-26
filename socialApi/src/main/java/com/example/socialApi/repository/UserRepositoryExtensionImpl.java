package com.example.socialApi.repository;

import com.example.socialApi.dto.FriendsDTO;
import com.example.socialApi.dto.QFriendsDTO;
import com.example.socialApi.model.QRelationships;
import com.example.socialApi.model.QUsers;
import com.example.socialApi.model.Users;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.socialApi.model.QRelationships.*;
import static com.example.socialApi.model.QUsers.*;

public class UserRepositoryExtensionImpl extends QuerydslRepositorySupport implements UserRepositoryExtension {


    public UserRepositoryExtensionImpl() {
        super(Users.class);
    }


    @Override
    public List<FriendsDTO> findFriends(Long userId) {
        List<FriendsDTO> fetch = from(users)
                .innerJoin(relationships).on(relationships.followUser.eq(users))
                .where(users.id.eq(userId))
                .limit(5)
                .select(new QFriendsDTO(
                        users.id,
                        users.nickname,
                        users.profilePic,
                        relationships.followedUser.id
                )).fetch();
        return fetch;
    }
}
