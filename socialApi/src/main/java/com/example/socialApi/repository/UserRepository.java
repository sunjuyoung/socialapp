package com.example.socialApi.repository;

import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<Users, Long> ,UserRepositoryExtension{

    Optional<Users> findByNickname(String username);

    boolean existsByNickname(String nickname);

    boolean existsByEmail(String email);


    List<Users> findTop3ByOrderByCreatedAtDesc();

//    @EntityGraph(attributePaths = {"followUser","followedUser"})
//    @Query("select u.createdAt,u.id,u.nickname," +
//            " r.followedUser.id, u.profilePic  " +
//            " from Users u " +
//            " inner join Relationships r " +
//            " on r.followUser.id = u.id" +
//            " where u.id = :id")
//    List<Users> findFriends(@Param("id") Long id);
}
