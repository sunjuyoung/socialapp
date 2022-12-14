package com.example.socialApi.service;

import com.example.socialApi.dto.CommentDTO;
import com.example.socialApi.model.Comments;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public List<CommentDTO> getComments(Long postId) {
        List<Comments> comments = commentRepository.findCommentsWithUsersAndPostsByPostsId(postId);
        if(comments.size() == 0  && comments == null){
            return null;
        }
        List<CommentDTO> commentDTOS = new ArrayList<>();
        comments.forEach(comment -> {
            commentDTOS.add(CommentDTO.builder()
                    .description(comment.getDescription())
                    .nickname(comment.getUsers().getNickname())
                    .profilePic(comment.getUsers().getProfilePic())
                    .id(comment.getId())
                    .userId(comment.getUsers().getId())
                    .modifiedBy(comment.getModifiedBy())
                    .build());
        });
        return commentDTOS;
    }

    public void saveComment(CommentDTO commentDTO,Long postId, Long userId) {
        Users users = Users.builder()
                .id(userId)
                .build();
        Posts posts = Posts.builder()
                .id(postId)
                .build();
        Comments comments = Comments.builder()
                .description(commentDTO.getDescription())
                .posts(posts)
                .users(users)
                .build();
        commentRepository.save(comments);

    }
}
