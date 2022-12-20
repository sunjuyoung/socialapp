package com.example.socialApi.service;

import com.example.socialApi.dto.RelationShipDTO;
import com.example.socialApi.model.Relationships;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.RelationShipRepository;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class RelationShipService {

    private final RelationShipRepository relationShipRepository;
    private final UserRepository userRepository;


    public void addRelationShip(Long followUserId, Long followedUserId) {
        Users users = userRepository.findById(followUserId).get();
        Users followedUser = Users.builder()
                .id(followedUserId)
                .build();
        Relationships relationships = Relationships.builder()
                .followUser(users)
                .followedUser(followedUser)
                .build();
        relationShipRepository.save(relationships);
    }

    public List<RelationShipDTO> getRelationShip(Long userId) {
        List<Relationships> relationships = relationShipRepository.findAllByFollowedId(userId);
        List<RelationShipDTO> dtoList = new ArrayList<>();
        for (Relationships relationship : relationships) {
            dtoList.add(RelationShipDTO.builder()
                    .followedUser(relationship.getFollowedUser().getId())
                    .followUser(relationship.getFollowUser().getId())
                    .build());
        }
        return dtoList;


    }

    public void deleteRelationShip(Long followUserId, Long followedUserId) {
        Users followUser = userRepository.findById(followUserId).get();
        Users followedUser = userRepository.findById(followedUserId).get();
        relationShipRepository.deleteByFollowUserAndFollowedUser(followUser,followedUser);
    }
}
