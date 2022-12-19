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

    @PostMapping
    public ResponseEntity<?> addRelationShip(@RequestBody RelationShipDTO relationShipDTO){
        relationShipService.addRelationShip(relationShipDTO);
        return ResponseEntity.ok().body("success");
    }
}
