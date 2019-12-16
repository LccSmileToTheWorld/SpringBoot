package org.study.springboot.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description:
 * @Author: Lcc
 * @Date: 2019/8/6
 * @Version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel
public class User implements Serializable {

    private Integer id;
    //隐藏属性
    @ApiModelProperty(hidden=true)
    private String name;
    private Integer age;
    private Date createTime;


}

