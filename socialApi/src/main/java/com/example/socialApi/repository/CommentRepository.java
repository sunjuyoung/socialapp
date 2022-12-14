package com.example.socialApi.repository;

import com.example.socialApi.model.Comments;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CommentRepository extends JpaRepository<Comments, Long> {


    @Query("select  c, u" +
            " from Comments c inner join c.users u on u.id = c.users.id " +
            " where c.posts.id = :id group by c.id ")
    List<Comments> findCommentsWithUsersAndPostsByPostsId(@Param("id") Long id);
}
