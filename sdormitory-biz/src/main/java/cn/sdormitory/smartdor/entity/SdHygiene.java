package cn.sdormitory.smartdor.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:05
 * @version：V1.0
 * 卫生表
 */
@Data
@TableName("sd_hygiene")
public class SdHygiene implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 卫生检查日期
     */
    @ApiModelProperty(value = "卫生检查日期")
    private String checkDate;

    /**
     * 第几栋楼
     */
    @ApiModelProperty(value = "第几栋楼")
    private String buildingNo;

    /**
     * 楼层
     */
    @ApiModelProperty(value = "楼层")
    private String storey;

    /**
     * 宿舍表ID
     */
    @ApiModelProperty(value = "宿舍表ID")
    private Long bdormitoryId;

    /**
     * 宿舍号
     */
    @ApiModelProperty(value = "宿舍号")
    private String dormitoryNo;

    /**
     * 总扣分
     */
    @ApiModelProperty(value = "总扣分")
    private double totalPdeduct;

    /**
     * 总得分
     */
    @ApiModelProperty(value = "总得分")
    private double totalScore;


    /**
     * 描述信息
     */
    @ApiModelProperty(value = "描述信息")
    private String remark;

    /**
     * 卫生检查录入员(当前登录用户)
     */
    @ApiModelProperty(value = "卫生检查录入员(当前登录用户)")
    private String createBy;

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

    /**
     * 卫生项ID列表
     */
    @ApiModelProperty(value = "卫生项ID列表")
    @TableField(exist = false)
    private List<Long> deductIds;

}
