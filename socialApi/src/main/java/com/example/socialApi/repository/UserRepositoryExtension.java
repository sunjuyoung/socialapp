package com.example.socialApi.repository;

import com.example.socialApi.dto.FriendsDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface UserRepositoryExtension {

    List<FriendsDTO> findFriends(Long userId);


    List<FriendsDTO> findRecommendUser(Long userId);
}
