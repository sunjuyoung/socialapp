package com.example.socialApi.controller;

import com.example.socialApi.dto.CommentDTO;
import com.example.socialApi.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping(value = "/{postId}")
    public ResponseEntity<?> getComments(@PathVariable("postId")Long postId){
        List<CommentDTO> commentDTOList = commentService.getComments(postId);
        return ResponseEntity.ok().body(commentDTOList);
    }

    @PostMapping(value = "/{postId}/{userId}")
    public ResponseEntity<?> saveComment(@PathVariable("postId")Long postId,
                                         @PathVariable("userId")Long userId,
                                         @RequestBody CommentDTO commentDTO){
        commentService.saveComment(commentDTO,postId,userId);
        return ResponseEntity.ok().body("success");
    }

}
