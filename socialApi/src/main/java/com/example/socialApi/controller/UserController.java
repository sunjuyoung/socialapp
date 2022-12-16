package com.example.socialApi.controller;

import com.example.socialApi.dto.ProfileDTO;
import com.example.socialApi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUserProfileById(@PathVariable("userId")Long userId){
        ProfileDTO profileDTO  = userService.getUserProfileById(userId);
        return ResponseEntity.ok().body(profileDTO);
    }
}
