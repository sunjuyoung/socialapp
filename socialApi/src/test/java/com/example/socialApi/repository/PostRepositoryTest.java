package com.example.socialApi.repository;

import com.example.socialApi.dto.PostDTO;
import com.example.socialApi.model.Posts;
import com.example.socialApi.model.QLikes;
import com.example.socialApi.model.QPosts;
import com.example.socialApi.model.QUsers;
import com.querydsl.core.Query;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @Test
    public void test(){
        List<PostDTO> allWithUserNameAndLike = postRepository.findAllWithUserNameAndLike(1L);
        for(PostDTO tuple : allWithUserNameAndLike){
            System.out.println(tuple.getNickname());
        }
    }

}