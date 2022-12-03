package com.example.socialApi.repository;

import com.example.socialApi.model.Comments;
import com.example.socialApi.model.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.Comment;

public interface CommentRepository extends JpaRepository<Comments, Long> {
}
