package com.example.socialApi.service;

import com.example.socialApi.dto.SignUpDTO;
import com.example.socialApi.dto.UserDTO;
import com.example.socialApi.model.Users;
import com.example.socialApi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Account userdetailservice.......");
        Optional<Users> users = userRepository.findByNickname(username);
        Users account = users.orElseThrow(()-> new UsernameNotFoundException("not found user"));
        UserDTO userDTO = new UserDTO(account);
        log.info(userDTO);
        return userDTO;

    }

    public void signUp(SignUpDTO signUpDTO) {
        signUpDTO.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        Users users = new Users(signUpDTO);
        userRepository.save(users);
    }
}
