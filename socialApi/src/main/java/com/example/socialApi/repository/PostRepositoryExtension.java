package com.example.socialApi.repository;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.model.Posts;
import com.querydsl.core.Tuple;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostRepositoryExtension {

    List<PostDTO> findAllWithUserNameAndLike(Long id);

    List<PostDTO> findAllByUserId(Long id);
}
