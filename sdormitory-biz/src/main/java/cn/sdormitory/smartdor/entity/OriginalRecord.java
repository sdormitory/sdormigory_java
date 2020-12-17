package cn.sdormitory.smartdor.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *  Created By ruanteng
 *  DateTime：2020/12/7
 *  过闸流水表
 */

@Data
@TableName("original_record")
public class OriginalRecord {

  /**
   * ID
   */
  @TableId
  private Long id;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 学号
   */
  private String studentNo;

  /**
   * 出/入时间(刷脸时间)
   */
  private Date accessDate;

  /**
   * 考勤状态，1正常，2缺勤
   */
  private String attenceStatus;

  /**
   * 同步时间
   */
  private Date createTime;



}
