package com.example.socialApi.repository;

import com.example.socialApi.model.Likes;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<Likes, Long> {


    @Query("select l,u " +
            " from  Likes l  " +
            " inner join Users u on l.users.id = u.id " +
            " where l.posts.id = :postId")
    List<Likes> findLikesAndUsersByPostsId(@Param("postId")Long postId);


    @Modifying
    @Query("delete from Likes l " +
            " where l.posts = :postId AND  l.users = :userId")
    void deleteByPostIdAndUserId(@Param("postId") Posts postId, @Param("userId") Users userId);

}
