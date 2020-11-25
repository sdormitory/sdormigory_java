package cn.sdormitory.sys.param;

import lombok.Data;

import java.util.Map;

/**
 * ClassName: SmsCodeParam
 */
@Data
public class SmsCodeParam {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 短信类型【非必须】
     */
    private Integer msgType;
    /**
     * 验证码
     */
    private String msgCode;
    /**
     * 参数
     */
    private Map<String, String> params;
}
