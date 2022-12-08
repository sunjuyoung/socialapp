package com.example.socialApi.repository;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.dto.QPostDTO;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.QLikes;
import com.example.socialApi.model.QPosts;
import com.example.socialApi.model.QUsers;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;


public class PostRepositoryExtensionImpl extends QuerydslRepositorySupport implements PostRepositoryExtension {

    public PostRepositoryExtensionImpl() {
        super(Posts.class);
    }

    @Override
    public List<PostDTO> findAllWithUserNameAndLike() {
        QPosts posts = QPosts.posts;
        QLikes likes = QLikes.likes;
        QUsers users = QUsers.users;

        List<PostDTO> postDTOList = from(posts)
                .innerJoin(posts.users, users)
                .leftJoin(likes).on(likes.posts.id.eq(posts.id))
                .groupBy(posts.id, users.nickname)
                .select(new QPostDTO(
                        posts.id,
                        posts.description,
                        posts.img,
                        likes.id.count().intValue(),
                        users.nickname,
                        posts.modifiedBy
                       ))
                .fetch();
        return postDTOList;
    }
}
