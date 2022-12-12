package com.example.socialApi.controller;


import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping
    public ResponseEntity<?> createPost(@RequestParam(value = "image",required = false) MultipartFile file,
                                        @RequestBody PostDTO postDTO){
        return null;
    }
//    @GetMapping(value = "/image/{fileName}")
//    public ResponseEntity<?> getImage(@PathVariable("fileName")String fileName) {
//        byte[] image = roomService.getImage(fileName);
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(image);
//    }
}
