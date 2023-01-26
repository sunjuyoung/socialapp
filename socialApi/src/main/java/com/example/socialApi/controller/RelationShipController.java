package com.example.socialApi.controller;

import com.example.socialApi.dto.RelationShipDTO;
import com.example.socialApi.service.RelationShipService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/relationship")
public class RelationShipController {

    private final RelationShipService relationShipService;


    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getRelationShip(@PathVariable("userId")Long userId){
        List<RelationShipDTO> relationShipDTOList = relationShipService.getRelationShip(userId);
        return ResponseEntity.ok().body(relationShipDTOList.stream().map(RelationShipDTO::getFollowUser));
    }

    @PostMapping("/{followUser}/{followedUser}")
    public ResponseEntity<?> addRelationShip(@PathVariable("followUser")Long followUser,
                                             @PathVariable("followedUser")Long followedUser){
        relationShipService.addRelationShip(followUser,followedUser);
        return ResponseEntity.ok().body("success");
    }
    @DeleteMapping("/{followUser}/{followedUser}")
    public ResponseEntity<?> deleteRelationShip(@PathVariable("followUser")Long followUser,
                                                @PathVariable("followedUser")Long followedUser){

        relationShipService.deleteRelationShip(followUser,followedUser);
        return ResponseEntity.ok().body("success");
    }


}
