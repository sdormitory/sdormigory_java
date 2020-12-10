package cn.sdormitory.basedata.entity;

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
 * @创建时间：2020/11/7 20:11
 * @version：V1.0
 * 宿舍表
 */
@Data
@TableName("b_dormitory")
public class BDormitory implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

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
     * 宿舍号
     */
    @ApiModelProperty(value = "宿舍号")
    private String dormitoryNo;

	/**
     * 宿管老师ID
     */
    @ApiModelProperty(value = "宿管老师ID")
    private Long dormitoryTeacherId;

    //宿管老师名称
    @TableField(exist = false)
    private String dormitoryTeacherName;

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
