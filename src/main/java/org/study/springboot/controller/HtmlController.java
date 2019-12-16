package org.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.study.springboot.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HtmlController
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/9
 * @Version 1.0
 */
@Controller
public class HtmlController {

    @RequestMapping("/to")
    public ModelAndView ThymeleafTest(){
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        user.setName("用户名");
        user.setAge(99);
        map.put("user", user);
       return new ModelAndView("/user", map);
    }
}
