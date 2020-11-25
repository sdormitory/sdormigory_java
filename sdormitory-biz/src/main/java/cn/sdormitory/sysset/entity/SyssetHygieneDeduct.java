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
 * @创建时间：2020/11/19 16:37
 * @version：V1.0
 * 宿舍卫生项扣分表
 */
@Data
@TableName("sysset_hygiene_deduct")
public class SyssetHygieneDeduct implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 检查扣分项
     */
    @ApiModelProperty(value = "检查扣分项")
    private String deductOption;

    /**
     * 扣分值
     */
    @ApiModelProperty(value = "扣分值")
    private double deductValue;

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
