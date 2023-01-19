package com.example.socialApi.repository;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> ,PostRepositoryExtension{


    @Query("SELECT p from Posts p inner join p.users u ")
    List<Posts> findPosts();

    @EntityGraph(attributePaths = {"users"})
    List<Posts> findByUsers(Users users);

    @EntityGraph(attributePaths = {"users"})
    List<Posts> findTop3ByUsersOrderByCreatedByDesc(Users users);
}
