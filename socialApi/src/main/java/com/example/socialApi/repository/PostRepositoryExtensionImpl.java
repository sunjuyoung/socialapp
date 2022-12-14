package com.example.socialApi.repository;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.dto.QPostDTO;
import com.example.socialApi.model.*;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.socialApi.model.QLikes.likes;

import static com.example.socialApi.model.QPosts.*;
import static com.example.socialApi.model.QPostsPhoto.*;
import static com.example.socialApi.model.QRelationships.*;
import static com.example.socialApi.model.QUsers.*;
import static org.springframework.util.ObjectUtils.isEmpty;


public class PostRepositoryExtensionImpl extends QuerydslRepositorySupport implements PostRepositoryExtension {

    public PostRepositoryExtensionImpl() {
        super(Posts.class);
    }

    @Override
    public List<PostDTO> findAllWithUserNameAndLike(Long id) {
        List<PostDTO> postDTOList = from(posts)

                .innerJoin(posts.users, users)
                .leftJoin(relationships).on(relationships.followedUser.eq(users))
                .leftJoin(likes).on(likes.posts.id.eq(posts.id))
                .where(
                        userIdEq(id)
                                .or(followIdEq(id))
                )
                .groupBy(posts.id, users.nickname,users.id)
                .select(new QPostDTO(
                        posts.id,
                        posts.description,
                        likes.id.count().intValue(),
                        users.nickname,
                        posts.modifiedBy,
                        users.id,
                        posts.img

                       ))
                .fetch();
        return postDTOList;
    }

    private BooleanExpression followIdEq(Long id) {
        return isEmpty(id) ? null : relationships.followUser.id.eq(id);
    }

    private BooleanExpression userIdEq(Long id) {
        return id != null ? users.id.eq(id) : null;
    }
}
