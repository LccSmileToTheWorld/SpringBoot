package org.study.springboot.service.impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ScheduleService
 * @Description: 任务测试
 * @Author: Lcc
 * @Date: 2019/8/7
 * @Version 1.0
 */
@Component
public class ScheduleService {

    @Scheduled(cron = "3 * * * * ?")
    public void scheduleTest0(){
        System.out.println("++++++schedule++++++每分钟3秒时执行一次");
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduleTest1(){
        System.out.println("++++++schedule++++++从0秒开始，每10秒执行一次");
    }

    @Scheduled(cron = "13,17 * * * * ?")
    public void scheduleTest2(){
        System.out.println("++++++schedule++++++每分钟13秒,17秒时执行一次");
    }

    @Scheduled(cron = "25-28 * * * * ?")
    public void scheduleTest3(){
        System.out.println("++++++schedule++++++每分钟25-28秒期间每秒执行一次");
    }
}
