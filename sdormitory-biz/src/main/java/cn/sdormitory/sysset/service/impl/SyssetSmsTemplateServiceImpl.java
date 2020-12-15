package cn.sdormitory.sysset.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.sysset.dao.SyssetSmsTemplateDao;
import cn.sdormitory.sysset.entity.SyssetSmsTemplate;
import cn.sdormitory.sysset.service.SyssetSmsTemplateService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/18 14:27
 * @version：V1.0
 */
@Slf4j
@Service("syssetSmsTemplateService")
public class SyssetSmsTemplateServiceImpl extends ServiceImpl<SyssetSmsTemplateDao, SyssetSmsTemplate> implements SyssetSmsTemplateService {
    @Override
    public IPage<SyssetSmsTemplate> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String smsType = (String) params.get("smsType");
        String status = (String) params.get("status");
        String smsTitle = (String) params.get("smsTitle");

        LambdaQueryWrapper<SyssetSmsTemplate> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(smsType)) {
            wrapper.eq(SyssetSmsTemplate::getSmsType, smsType);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SyssetSmsTemplate::getStatus, status);
        }
        if (StrUtil.isNotEmpty(smsTitle)) {
            wrapper.like(SyssetSmsTemplate::getSmsTitle, smsTitle);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SyssetSmsTemplate getSyssetSmsTemplateById(Long id) {
        return getById(id);
    }

    @Override
    public int create(SyssetSmsTemplate syssetSmsTemplate) {
        return this.baseMapper.insert(syssetSmsTemplate);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SyssetSmsTemplate syssetSmsTemplate) {
        syssetSmsTemplate.setId(id);
        return this.baseMapper.updateById(syssetSmsTemplate);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        SyssetSmsTemplate syssetSmsTemplate= new SyssetSmsTemplate();
        syssetSmsTemplate.setId(id);
        syssetSmsTemplate.setStatus(status);
        return this.baseMapper.updateById(syssetSmsTemplate);
    }

    @Override
    public SyssetSmsTemplate getBySmsTitle(String smsTitle) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SyssetSmsTemplate>().eq(SyssetSmsTemplate::getSmsTitle, smsTitle));
    }

    @Override
    public SyssetSmsTemplate getBySmsTypee(int smsType) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SyssetSmsTemplate>().eq(SyssetSmsTemplate::getSmsType, smsType));
    }
}
