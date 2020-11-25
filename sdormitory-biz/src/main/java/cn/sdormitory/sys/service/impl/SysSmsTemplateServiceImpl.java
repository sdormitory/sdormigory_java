package cn.sdormitory.sys.service.impl;

import cn.sdormitory.common.exception.ApiException;
import cn.sdormitory.sys.vo.SmsTemplateDto;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.sdormitory.common.exption.SmsExceptionEnum;
import cn.sdormitory.common.utils.BeanUtils;
import cn.sdormitory.sys.dao.SysSmsTemplateDao;
import cn.sdormitory.sys.entity.SysSmsTemplate;
import cn.sdormitory.sys.service.SysSmsTemplateService;
import org.springframework.stereotype.Service;

/**
 * 短信模板
 */
@Service("sysSmsTemplateService")
public class SysSmsTemplateServiceImpl extends ServiceImpl<SysSmsTemplateDao, SysSmsTemplate> implements SysSmsTemplateService {


    @Override
    public SmsTemplateDto geSmsTemplateByCode(Long code) {
        if (code == null) {
            throw new ApiException(SmsExceptionEnum.MSG_TYPE_ERROR);
        }

        SysSmsTemplate smsTemplate = this.baseMapper.selectOne(new LambdaQueryWrapper<SysSmsTemplate>()
                .eq(SysSmsTemplate::getValue, code)
        );

        return BeanUtils.transform(SmsTemplateDto.class, smsTemplate);
    }


}