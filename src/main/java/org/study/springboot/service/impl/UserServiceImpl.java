package org.study.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.study.springboot.entity.User;
import org.study.springboot.mapper.UserMapper;
import org.study.springboot.service.UserService;
import org.study.springboot.utils.RedisUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/6
 * @Version 1.0
 */
@Service
//@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisUtils redisUtils;

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User getUser(Integer id) {
        if (redisUtils.hasKey(id.toString())){
            return (User) redisUtils.get(id.toString());
        }
        User user = userMapper.getUser(id);
        redisUtils.set(id.toString(), user, 86400);
        logger.info("将查询出的用户信息放入缓存中");
        return user;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public Integer addUser(User user) {
        int count = userMapper.addUser(user);
        redisUtils.set(user.getId().toString(), user);
        logger.info("将新增的用户信息添加到缓存中");
        return count;
    }

    @Override
    public Integer updateUser(User user) {
        int count = userMapper.updateUser(user);
        redisUtils.set(user.getId().toString(), user);
        logger.info("将修改的用户信息添加到缓存中");
        return count;
    }

    @Transactional
    @Override
    public Integer deleteUser(Integer id) {
        int count = userMapper.deleteUser(id);
        redisUtils.del(id.toString());
        logger.info("将指点的用户信息从缓存中删除");
        transactionTest();
        return count;
    }

    public void transactionTest(){
        System.out.println("=========报错前");
        User user = new User();
        user.setName("qwe");
        user.setAge(101);
        user.setCreateTime(new Date());
        userMapper.addUser(user);
        int a = 1/0;
        System.out.println("--------");
    }
}
