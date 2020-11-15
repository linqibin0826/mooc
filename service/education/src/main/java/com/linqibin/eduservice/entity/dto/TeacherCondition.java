package com.linqibin.eduservice.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 多条件组合查询,前端传入条件值
 * 条件值封装为一个VO对象,将VO传递到接口
 *
 * @author linqibin&youmei
 * @creed: ProjectForSDDM
 * @date 2020/10/14
 */
@ApiModel(value = "Teacher多条件查询对象", description = "讲师查询对象封装")
@Data
public class TeacherCondition {
    @ApiModelProperty(value = "教师名称,ambiguous")
    private String name;

    @ApiModelProperty(value = "头衔 1.高级讲师 2.首席讲师")
    private Integer level;

    @ApiModelProperty(value = "开始时间", example = "2020-10-10 10:10:10")
    private String begin;  //使前端传入的参数无需转换

    @ApiModelProperty(value = "结束时间", example = "2020-10-10 10:10:10")
    private String end;
}
