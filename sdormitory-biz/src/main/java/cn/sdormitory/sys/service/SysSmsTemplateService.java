package cn.sdormitory.sys.service;

import cn.sdormitory.sys.vo.SmsTemplateDto;

/**
 * 短信模板
 */
public interface SysSmsTemplateService {

    /**
     *
     * desc   获取短信模板
     *
     * @author honghh
     * date 2019-08-30 14:28
     * @param value
     * @return
     */
    SmsTemplateDto geSmsTemplateByCode(Long value);
}

