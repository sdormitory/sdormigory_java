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
 * @创建时间：2020/11/18 14:01
 * @version：V1.0
 * 短信模板设置
 */
@Data
@TableName("sysset_sms_template")
public class SyssetSmsTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId
    @ApiModelProperty(value = "ID")
    private Long id;

    /**
     * 短信模板类型
     */
    @ApiModelProperty(value = "短信模板类型")
    private String smsType;

    /**
     * 短信标题
     */
    @ApiModelProperty(value = "短信标题")
    private String smsTitle;

    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    private String smsContent;

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
