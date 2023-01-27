package com.example.socialApi.service;

import com.example.socialApi.dto.FriendsDTO;
import com.example.socialApi.dto.ProfileDTO;

import java.util.List;

public interface UserService {
    ProfileDTO getUserProfileById(Long userId);

    void updateProfile(Long userId, ProfileDTO profileDTO);

    List<FriendsDTO> getRelUsers(Long userId);

    List<FriendsDTO> getRecommendUser(Long userId);
}
