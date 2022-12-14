package com.example.socialApi.controller;


import com.example.socialApi.dto.CreatePostDTO;
import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;


    @GetMapping(value = "/list/{id}")
    public ResponseEntity<?> getPosts(@PathVariable("id")Long id){
        List<PostDTO> postDTOList = postService.getPosts(id);
        return ResponseEntity.ok().body(postDTOList);
    }

/*    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> createPost(@PathVariable("userId")Long userId
            ,@RequestBody CreatePostDTO createPostDTO){
        log.info("=====");
        log.info(createPostDTO.getDescription());
        log.info(createPostDTO.getImg());
        postService.createPost(createPostDTO,userId);
        return ResponseEntity.ok().body("success");
    }*/
    @PostMapping(value = "/{userId}")
    public ResponseEntity<?> createPost(@PathVariable("userId")Long userId
            ,@RequestBody CreatePostDTO createPostDTO){
       postService.createPosts(createPostDTO,userId);
        return ResponseEntity.ok().body("success");
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        Long newImageId = postService.uploadImage(file);
        return ResponseEntity.ok().body(newImageId);
    }

    @PostMapping("/image/{postId}")
    public ResponseEntity<?> uploadImage(@PathVariable(value = "postId")Long postId
            ,@RequestParam("image")MultipartFile file) throws IOException {
        Long newImageId = postService.uploadImagePost(file,postId);
        return ResponseEntity.ok().body(newImageId);
    }

    
    @GetMapping(value = "/image/{fileName}")
    public ResponseEntity<?> getImage(@PathVariable("fileName")String fileName) {
        byte[] image = postService.getImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(image);
    }
}
