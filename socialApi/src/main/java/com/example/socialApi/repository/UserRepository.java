package com.example.socialApi.repository;

import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByNickname(String username);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);
}
