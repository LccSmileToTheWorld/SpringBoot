package org.study.springboot.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类.
 *
 * @author lcc
 * @version 1.0
 * @date 2020/1/20
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel
public class User implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 姓名
     */
    @ApiModelProperty(hidden=true)
    @Excel(name="姓名")
    private String name;
    /**
     * 年龄
     */
    @Excel(name = "年龄")
    private Integer age;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间")
    private Date createTime;


}

