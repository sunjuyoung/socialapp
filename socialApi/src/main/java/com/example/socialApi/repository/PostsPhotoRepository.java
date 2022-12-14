package com.example.socialApi.repository;

import com.example.socialApi.model.PostsPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsPhotoRepository extends JpaRepository<PostsPhoto, Long> {
    Optional<PostsPhoto> findByName(String fileName);
}
