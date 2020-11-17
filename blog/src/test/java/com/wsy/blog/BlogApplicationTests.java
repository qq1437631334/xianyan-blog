package com.wsy.blog;

import com.wsy.blog.dao.CommentDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

    @Autowired
    private CommentDao commentDao;
    @Test
    void contextLoads() {
    }

}
