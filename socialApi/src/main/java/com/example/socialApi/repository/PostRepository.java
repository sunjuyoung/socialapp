package com.example.socialApi.repository;

import com.example.socialApi.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> ,PostRepositoryExtension{


    @Query("SELECT p from Posts p inner join p.users u ")
    List<Posts> findPosts();
}
