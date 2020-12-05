package cn.sdormitory.smartdor.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * create By: Zhou Yinsen
 * date: 11/28/2020 10:42 AM
 * description:
 */
@Data
@TableName("sd_device")
public class SdDevice {

  /**
   * id
   */
  private long id;

  /**
   * 设备IP地址
   */
  private String deviceIpAddress;

  /**
   * 设备出入类型(1:出门 2:进门)
   */
  private String deviceAccessType;

  /**
   * 设备号
   */
  private String deviceNo;

  /**
   * 设备名称
   */
  private String deviceName;

  /**
   * 活体判断(0:活体,1非活体(活体需要双目摄像头支持)，默认0)
   */
  private String cameraDetectType;

  /**
   * 开门类型
   * 0:继电器,
   * 26:维根26,
   * 34:维根34,
   * 66:维根66,
   * 260:继电器+维根26,
   * 261:继电器+字节倒序维根26,
   * 340:继电器+维根34,
   * 341:继电器+字节倒序维根34,
   * 660:继电器+维根66，
   * 661:继电器+字节倒序维根66，
   * 默认340
   */
  private String doorType;

  /**
   * 终端识别失败后的提示语
   */
  private String tipsPairFail;

  /**
   * 图片质量:默认0.72
   */
  private double picQualityRate;

  /**
   * 识别成功后开门(0:开门,1:不开门)
   */
  private String pairSuccessOpenDoor;

  /**
   * 身份证比对阈值(机器需要对应模块)，默认0.5
   */
  private double idCardFaceFeaturePairNumber;

  /**
   * 设备维护时间(指定时间设备自动重启 默认 00:00)
   */
  private String deviceDefendTime;

  /**
   * 识别成功后开门持续时间(单位:毫秒，默认1000)
   */
  private double openDoorContinueTime;

  /**
   * 人脸比对时候的阈值,默认0.5
   */
  private double faceFeaturePairNumber;

  /**
   * 设备声音大小(0-100)，默认100
   */
  private long deviceSoundSize;

  /**
   * 人脸识别成功或失败后等待的时间(单位:毫秒，默认2000)
   */
  private double faceFeaturePairSuccessOrFailWaitTime;

  /**
   * 开门条件：0:人脸 ,2:工号,3:人脸+身份证,4:人脸+工号(根据具体机器)，默认2
   */
  private String openDoorType;

  /**
   * 补光灯设置(格式:HH:mm-HH:mm,每天可设置多段，用分号区分；例如：06:06-07:00;14:30-15:30)
   */
  private String fillLightTimes;

  /**
   * 息屏设置(息屏时间段，为 00:00-00:00 时设备 不息屏 ;只能设置一组)
   */
  private String lowPowerTimes;

  /**
   * 识别距离(识别距离=人脸宽度)
   */
  private double beginRecoDistance;

  /**
   * 识别成功提示声音(需要设备联网认证成功后提示(不是连接后台,而是能上网)),例:识别成功,@(人名替代符)
   */
  private String appWelcomeMsg;

  /**
   * 识别功能控制开关(0:设备将不进行任何识别0:关闭1:打开默认:1)
   */
  private String recognitionSwitch;

  /**
   * 状态(0:无效 1：有效)
   */
  private String status;

  /**
   * 创建时间
   */
  @ApiModelProperty(value = "创建时间")
  @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
  @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
  private Date createTime;

  /**
   * 修改时间
   */
  @ApiModelProperty(value = "修改时间")
  @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
  @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
  private Date modifyTime;

  /**
   * 用于从闸机上接收数据,对应设备号字段
   */
  @TableField(exist = false)
  private String sn;

  /**
   * 用于从闸机上接收数据,对应设备名称字段
   */
  @TableField(exist = false)
  private String name;



}
