package cn.sdormitory.sys.vo;

import lombok.Data;

/**
 * ClassName: SmsTemplateDto
 */
@Data
public class SmsTemplateDto {

    /**
     * 编号
     */
    private Long value;
    /**
     * 作用
     */
    private String name;
    /**
     * 短信签名
     */
    private String signName;
    /**
     * 来源平台
     */
    private Integer source;
    /**
     * 短信模板CODE
     */
    private String templateCode;
    /**
     * 第三方返回结果
     */
    private String content;
}
