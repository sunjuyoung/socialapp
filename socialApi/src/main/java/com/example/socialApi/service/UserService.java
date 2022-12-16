package com.example.socialApi.service;

import com.example.socialApi.dto.ProfileDTO;

public interface UserService {
    ProfileDTO getUserProfileById(Long userId);
}
