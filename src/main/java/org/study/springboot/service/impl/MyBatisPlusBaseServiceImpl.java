package org.study.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.study.springboot.entity.User;
import org.study.springboot.mapper.MybatisPlusBaseMapper;
import org.study.springboot.service.MyBatisPlusBaseService;

/**
 * @ClassName: MyBatisPlusBaseServiceImpl
 * @Description:
 * @Author: Lcc
 * @Date: 2020/1/9
 * @Version 1.0
 */
@Service("myBatisPlusBaseService")
public class MyBatisPlusBaseServiceImpl extends ServiceImpl<MybatisPlusBaseMapper, User> implements MyBatisPlusBaseService {

}
