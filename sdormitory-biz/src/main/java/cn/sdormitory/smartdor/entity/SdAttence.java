package cn.sdormitory.smartdor.entity;


import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created By：ruanteng
 * DateTime：2020/11/27
 * @version：V1.0
 * 考勤表
 */

@Data
@TableName("sd_attence")
public class SdAttence {

  /**
   * ID
   */
  @TableId
  @ApiModelProperty(value = "ID")
  private Long id;

  /**
   * 设备号
   */
  @ApiModelProperty(value = "设备号")
  private String deviceNo;

  /**
   * 学号
   */
  @ApiModelProperty(value = "学号")
  private String studentNo;

  /**
   * 学生姓名
   */
  @TableField(exist = false)
  @ApiModelProperty(value = "学生姓名")
  private String studentName;

  /**
   * 出/入时间(刷脸时间)
   */
  @ApiModelProperty(value = "出/入时间(刷脸时间)")
  @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
  private Date accessDate;

  /**
   * 考勤状态
   */
  @ApiModelProperty(value = "考勤状态")
  private String attenceStatus;

  /**
   * 缺勤处理
   */
  @ApiModelProperty(value = "缺勤处理")
  private String absenceProcessStatus;

  /**
   * 处理人
   */
  @ApiModelProperty(value = "处理人")
  private String processPerson;

  /**
   * 处理描述
   */
  @ApiModelProperty(value = "处理描述")
  private String processDesc;

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
