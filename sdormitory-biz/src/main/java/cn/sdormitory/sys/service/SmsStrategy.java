package cn.sdormitory.sys.service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import cn.sdormitory.sys.vo.SmsTemplateDto;
import cn.sdormitory.sys.param.SmsParam;

/**
 * ClassName: SmsStrategy
 * Description:
 */
public interface SmsStrategy {

    /**
     * 第三方发送短信策略
     *
     * @param smsParam
     * @param smsTemplate
     * @return 返回第三方发送结果
     */
    SendSmsResponse send(SmsParam smsParam, SmsTemplateDto smsTemplate);
}
