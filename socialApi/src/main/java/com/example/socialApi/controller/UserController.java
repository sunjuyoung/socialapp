package com.example.socialApi.controller;

import com.example.socialApi.dto.FriendsDTO;
import com.example.socialApi.dto.ProfileDTO;
import com.example.socialApi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserProfileById(@PathVariable("userId")Long userId){
        ProfileDTO profileDTO  = userService.getUserProfileById(userId);
        return ResponseEntity.ok().body(profileDTO);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<?> updateProfile(@PathVariable("userId")Long userId,
                                           @RequestBody ProfileDTO profileDTO){
        userService.updateProfile(userId,profileDTO);
        return ResponseEntity.ok().body("updated");
    }

    @GetMapping(value = "/rel/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRelUsers(@PathVariable("userId")Long userId){
        List<FriendsDTO> friendsDTOList  = userService.getRelUsers(userId);
        return ResponseEntity.ok().body(friendsDTOList);
    }

    @GetMapping(value = "/recommendUser/{userId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRecommendUser(@PathVariable("userId")Long userId){
        List<FriendsDTO> friendsDTOList  = userService.getRecommendUser(userId);
        return ResponseEntity.ok().body(friendsDTOList);
    }

}
