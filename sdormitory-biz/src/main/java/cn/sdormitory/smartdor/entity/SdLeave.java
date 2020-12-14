package cn.sdormitory.smartdor.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 17:34
 * @version：V1.0
 * 请假表
 */
@Data
@TableName("sd_leave")
public class SdLeave implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private String studentNo;

    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    /**
     * 学生手机号
     */
    @ApiModelProperty(value = "学生手机号")
    private String studentPhone;

    /**
     * 请假类型
     */
    @ApiModelProperty(value = "请假类型")
    private String leaveType;

    /**
     * 请假日期
     */
    @ApiModelProperty(value = "请假日期")
    private String leaveDate;



    /**
     * 请假原因
     */
    @ApiModelProperty(value = "请假原因")
    private String leaveReason;

    /**
     * 状态：1家长确认中-2家长确认通过-3班主任审核通过、4家长驳回、5作废
     */
    @ApiModelProperty(value = "状态：1家长确认中-2家长确认通过-3班主任审核通过、4家长驳回、5作废")
    private String status;

    /**
     * 家长描述信息
     */
    @ApiModelProperty(value = "家长描述信息")
    private String parentDesc;

    /**
     * 班主任描述信息
     */
    @ApiModelProperty(value = "班主任描述信息")
    private String teacherDesc;

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
