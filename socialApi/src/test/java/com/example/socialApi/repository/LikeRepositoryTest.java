package com.example.socialApi.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikeRepositoryTest {

    @Autowired
    private LikeRepository likeRepository;

    @Test
    public void test() throws Exception{
        //given
     likeRepository.findLikesAndUsersByPostsId(1L);
        //when

        //then
    }
}