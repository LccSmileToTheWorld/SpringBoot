package org.study.springboot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.study.springboot.entity.User;
import org.study.springboot.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 用户模块Controller.
 *
 * @author lcc
 * @version 1.0
 * @date 2020/1/15
 */
@RestController
@Api(value = "api.value: 用户模块", tags = {"api.tags: 用户模块"})
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "ApiOperation.value: 查询用户", notes = "ApiOperation.notes: 根据id查询")
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @GetMapping("/user")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/addUser")
    public User addUser(User user) {
        userService.addUser(user);
        return user;
    }

    @GetMapping("/updateUser")
    public User updateUser(User user) {
        userService.updateUser(user);
        return user;
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        int count = userService.deleteUser(id);
        if (count == 1){
            return "删除成功";
        }
        return "删除失败";
    }

    @GetMapping("/user/export")
    public void export(HttpServletResponse response){
        //Content-Type：application/octet-stream  二进制流文件
        //response.setHeader("Content-Type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //设置编码
        response.setCharacterEncoding("UTF-8");
        //设置下载文件名称
        response.setHeader("Content-Disposition", "attachment;filename=user.txt");
        File file = new File("C:\\Users\\lcc\\Desktop\\study\\问题.txt");
        try (FileInputStream in = new FileInputStream(file);
             BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream())) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                out.write(dataBuffer, 0, bytesRead);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}