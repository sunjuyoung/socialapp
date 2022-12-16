package com.example.socialApi.service;


import com.example.socialApi.dto.ProfileDTO;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public ProfileDTO getUserProfileById(Long userId) {
        Users users = userRepository.findById(userId).get();
        return modelMapper.map(users,ProfileDTO.class);
    }
}
