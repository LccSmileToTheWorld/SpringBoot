package org.study.springboot.service;

import org.study.springboot.entity.User;

import java.util.List;

/**
 * @ClassName: UserService
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/6
 * @Version 1.0
 */
public interface UserService {

    /**
     * <p> 根据客户id查询客户信息.
     *
     * @param id 客户id
     * @return {@link User}
     */
    User getUser(Integer id);

    /**
     * <p>
     *
     * @param
     * @return {@link List< User>}
     */
    List<User> getUsers();

    /**
     * @Author: Lcc
     * @Description: 新增用户active
     * @Date: 2019/8/7
     * @param user
     * @Return: java.lang.Integer
     */
    Integer addUser(User user);

    /**
     * @Author: Lcc
     * @Description: 修改用户
     * @Date: 2019/8/7
     * @param user
     * @Return: java.lang.Integer
     */
    Integer updateUser(User user);

    /**
     * @Author: Lcc
     * @Description: 删除用户
     * @Date: 2019/8/7
     * @param id
     * @Return: java.lang.Integer
     */
    Integer deleteUser(Integer id);
}
