package com.example.socialApi.repository;

import com.example.socialApi.model.Comments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;




    @Test
    public void test() throws Exception{
        //given
        commentRepository.findCommentsWithUsersAndPostsByPostsId(1L);
    }
}