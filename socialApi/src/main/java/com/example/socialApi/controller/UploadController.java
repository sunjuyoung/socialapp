package com.example.socialApi.controller;


import com.example.socialApi.dto.CreatePostDTO;
import com.example.socialApi.dto.UploadFileDTO;
import com.example.socialApi.dto.UploadResultDTO;
import com.example.socialApi.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@Log4j2
@RequiredArgsConstructor
public class UploadController {

    private final PostService postService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload( MultipartFile files) {
        UploadResultDTO uploadResultDTO = postService.uploadFile(files);
        return ResponseEntity.ok().body(uploadResultDTO.getLink());
    }

}
