package com.example.socialApi.controller;


import com.example.socialApi.dto.LikeDTO;
import com.example.socialApi.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    @GetMapping(value = "/{postId}")
    public ResponseEntity<?> getLikes(@PathVariable("postId") Long postId){
       List<LikeDTO> likeDTOList =  likeService.getLikes(postId);
        return ResponseEntity.ok().body(likeDTOList.stream().map((likeDTO -> likeDTO.getUserId())));
    }

    @PostMapping(value = "/{postId}/{userId}")
    public ResponseEntity<?> saveLike(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId){
        log.info("=======================");
        log.info(postId);
        likeService.saveLikes(postId,userId);
        return ResponseEntity.ok().body("success");
    }

    @DeleteMapping(value = "/{postId}/{userId}")
    public ResponseEntity<?> deleteLike(@PathVariable("postId") Long postId,@PathVariable("userId") Long userId){
        log.info("=======================");
        log.info(postId);
        likeService.deleteLike(postId,userId);
        return ResponseEntity.ok().body("success");
    }

}
