package org.study.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.study.springboot.entity.User;

import java.util.List;

/**
 * @ClassName: UserMapper
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/6
 * @Version 1.0
 */

@Repository
public interface UserMapper {

    /**
     * @Author: Lcc
     * @Description: 根据id查询用户
     * @Date: 2019/8/6
     * @param id
     * @Return: org.study.springboot.entity.User
     */
    User getUser(@Param("id") Integer id);

    /**
     * @Author: Lcc
     * @Description: 查询所有用户
     * @Date: 2019/8/7
     * @param
     * @Return: java.util.List<org.study.springboot.entity.User>
     */
    List<User> getUsers();

    /**
     * @Author: Lcc
     * @Description: 新增用户
     * @Date: 2019/8/7
     * @param user
     * @Return: java.lang.Integer
     */
    Integer addUser(@Param("user") User user);

    /**
     * @Author: Lcc
     * @Description: 更新用户
     * @Date: 2019/8/7
     * @param user
     * @Return: java.lang.Integer
     */
    Integer updateUser(@Param("user") User user);

    /**
     * @Author: Lcc
     * @Description: 删除用户
     * @Date: 2019/8/7
     * @param id
     * @Return: java.lang.Integer
     */
    Integer deleteUser(@Param("id") Integer id);
}
