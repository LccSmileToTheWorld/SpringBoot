package org.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.springboot.entity.User;
import org.study.springboot.service.MyBatisPlusBaseService;

/**
 * @ClassName: MybatisPlusCRUDController
 * @Description: 测试mybatis_plus通用CRUD
 * @Author: Lcc
 * @Date: 2020/1/9
 * @Version 1.0
 */
@RestController
@RequestMapping("/mybatisPlus")
public class MybatisPlusCRUDController {
    @Autowired
    private MyBatisPlusBaseService service;

    @GetMapping("/user")
    public User user(Integer id){
        return service.getById(id);
    }
}
