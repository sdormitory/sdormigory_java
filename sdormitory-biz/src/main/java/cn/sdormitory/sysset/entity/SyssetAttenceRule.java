package cn.sdormitory.sysset.entity;

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
 * @创建时间：2020/11/26 15:57
 * @version：V1.0
 * 考勤规则表
 */
@Data
@TableName("sysset_attence_rule")
public class SyssetAttenceRule implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 考勤规则类型(1:正常 2:放假)
     */
    @ApiModelProperty(value = "考勤规则类型(1:正常 2:放假)")
    private String attenceRuleType;

    /**
     * 考勤规则名称
     */
    @ApiModelProperty(value = "考勤规则名称")
    private String attenceRuleName;

    /**
     * 正常考勤日期(星期几，来自数据字典表)
     */
    @ApiModelProperty(value = "正常考勤日期(星期几，来自数据字典表)")
    private String attenceDay;

    /**
     * 正常考勤开始时间
     */
    @ApiModelProperty(value = "正常考勤开始时间")
    private String attenceStartTime;

    /**
     * 正常考勤结束时间
     */
    @ApiModelProperty(value = "正常考勤结束时间")
    private String attenceEndTime;

    /**
     * 放假开始时间
     */
    @ApiModelProperty(value = "放假开始时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date holidayStartDate;

    /**
     * 放假结束时间
     */
    @ApiModelProperty(value = "放假结束时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private Date holidayEndDate;

    /**
     * 状态：0->禁用；1->启用
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
