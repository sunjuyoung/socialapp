package com.example.socialApi.repository;

import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByNickname(String username);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);


    List<Users> findTop3ByOrderByCreatedAtDesc();
}
