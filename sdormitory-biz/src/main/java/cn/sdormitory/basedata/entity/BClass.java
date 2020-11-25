package cn.sdormitory.basedata.entity;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/2 17:11
 * @version：V1.0
 * 班级表
 */

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("b_class")
public class BClass implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称")
    private String className;

    /**
     * 班主任ID
     */
    @ApiModelProperty(value = "班主任ID")
    private Long classTeacherId;

    //班主任名称
    @TableField(exist = false)
    private String classTeacherName;

    /**
     * 启用状态：0->禁用；1->启用
     */
    @ApiModelProperty(value = "启用状态：0->禁用；1->启用")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date modifyTime;


}
