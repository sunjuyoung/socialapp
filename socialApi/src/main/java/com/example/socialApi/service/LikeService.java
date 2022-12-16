package com.example.socialApi.service;

import com.example.socialApi.dto.LikeDTO;
import com.example.socialApi.model.Likes;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.LikeRepository;
import com.example.socialApi.repository.PostRepository;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<LikeDTO> getLikes(Long postId) {
        List<Likes> likes = likeRepository.findLikesAndUsersByPostsId(postId);
        List<LikeDTO> likeDTOS = new ArrayList<>();
        if(likes.size() > 0){
            for (Likes like : likes) {
                likeDTOS.add(LikeDTO.builder()
                        .id(like.getId())
                        .userId(like.getUsers().getId())
                        .build());
            }
        }
            return likeDTOS;

    }

    public void saveLikes(Long postId, Long userId) {
        Users users = Users.builder()
                .id(userId)
                .build();
        Posts posts = Posts.builder()
                .id(postId)
                .build();
        Likes likes = Likes.builder()
                .posts(posts)
                .users(users)
                .build();
        likeRepository.save(likes);

    }

    public void deleteLike(Long postId, Long userId) {
        Users users = userRepository.findById(userId).get();
        Posts posts = postRepository.findById(postId).get();
        likeRepository.deleteByPostIdAndUserId(posts,users);
    }
}
