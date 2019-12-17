package org.study.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.study.springboot.entity.User;
import org.study.springboot.utils.DateUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCoreApplicationTests {

    @Autowired
    DataSource dataSource;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void dateTest(){
        System.out.println(DateUtils.getToday());
        System.out.println(new User() == new User());
    }

}
