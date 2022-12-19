package com.example.socialApi.repository;

import com.example.socialApi.model.Relationships;
import com.example.socialApi.model.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RelationShipRepository extends JpaRepository<Relationships,Long> {

    @EntityGraph(attributePaths = {"followedUser","followUser"})
    @Query("select r " +
            " from Relationships r  " +
            " where r.followedUser.id = :userId " +
            " group by r.followUser.id")
    List<Relationships> findAllByFollowedId(@Param("userId")Long userId);

}
