package org.study.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.springboot.util.MyEnum;

/**
 * @ClassName: SwitchEnumController
 * @Description:
 * @Author: Lcc
 * @Date: 2020/1/3
 * @Version 1.0
 */
@Controller
public class SwitchEnumController {

    @RequestMapping("/getSeason")
    @ResponseBody
    public String getSeason(Integer index){
        String season = MyEnum.Season.spring.value;
        switch (MyEnum.Season.getSeasonByIndex(index)){
            case spring:
                break;
            case summer:
                season = MyEnum.Season.summer.value;
                break;
            case autumn:
                season = MyEnum.Season.autumn.value;
                break;
            default:
                season = MyEnum.Season.winter.value;
                break;
        }
        return season;
    }
}
