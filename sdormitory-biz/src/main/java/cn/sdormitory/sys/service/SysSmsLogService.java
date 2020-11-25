package cn.sdormitory.sys.service;

import cn.sdormitory.sys.param.SmsParam;
import cn.sdormitory.sys.vo.SmsTemplateDto;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;

/**
 * 短信日志表
 */
public interface SysSmsLogService {
    /**
     *
     * desc 保存短信发送日志
     *
     * @author honghh
     * date 2019-08-30 14:38
     * @param smsParam
     * @param smsTemplate
     * @param sendSmsResponse
     * @return
     */
    Boolean addSmsLog(SmsParam smsParam, SmsTemplateDto smsTemplate, SendSmsResponse sendSmsResponse);
}

