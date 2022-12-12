package com.example.socialApi.service;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.model.Posts;
import com.example.socialApi.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDTO> getPosts(Long id) {
        List<PostDTO> postDTOList = postRepository.findAllWithUserNameAndLike(id);
        return postDTOList;
    }
}
