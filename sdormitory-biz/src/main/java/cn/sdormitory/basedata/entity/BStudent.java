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
 * @创建时间：2020/11/9 17:12
 * @version：V1.0
 * 学生表
 */
@Data
@TableName("b_student")
public class BStudent implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 学生姓名
     */
    @ApiModelProperty(value = "学生姓名")
    private String studentName;

    /**
     * 学生密码
     */
    @ApiModelProperty(value = "学生密码")
    private String studentPassword;

    /**
     * 学号
     */
    @ApiModelProperty(value = "学号")
    private String studentNo;

    /**
     * 所属班级ID
     */
    @ApiModelProperty(value = "所属班级ID")
    private Long classId;

    /**
     * 班级名称
     */
    @ApiModelProperty(value = "班级名称")
    private String className;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idcard;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String sex;
    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Long age;
    /**
     * 家长姓名
     */
    @ApiModelProperty(value = "家长姓名")
    private String parentName;
    /**
     * 家长电话
     */
    @ApiModelProperty(value = "家长电话")
    private String parentPhone;
    /**
     * 家长密码
     */
    @ApiModelProperty(value = "家长密码")
    private String parentPassword;

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
    private String bdormitoryId;

    /**
     * 宿舍号
     */
    @ApiModelProperty(value = "宿舍号")
    private String dormitoryNo;

    /**
     * 照片
     */
    @ApiModelProperty(value = "照片")
//    private String photo;
    private byte[] photo;

    /**
     * 启用状态：0->无效；1->有效
     */
    @ApiModelProperty(value = "启用状态：0->无效；1->有效")
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
