package cn.sdormitory.sysset.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.utils.DateTimeUtils;
import cn.sdormitory.sysset.dao.SyssetAttenceRuleDao;
import cn.sdormitory.sysset.entity.SyssetAttenceRule;
import cn.sdormitory.sysset.service.SyssetAttenceRuleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/26 16:12
 * @version：V1.0
 */
@Slf4j
@Service("syssetAttenceRuleService")
public class SyssetAttenceRuleServiceImpl extends ServiceImpl<SyssetAttenceRuleDao, SyssetAttenceRule> implements SyssetAttenceRuleService {

    @Override
    public IPage<SyssetAttenceRule> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String attenceRuleType = (String) params.get("attenceRuleType");
        String attenceRuleName = (String) params.get("attenceRuleName");
        String status = (String) params.get("status");

        LambdaQueryWrapper<SyssetAttenceRule> wrapper = new LambdaQueryWrapper<>();


        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SyssetAttenceRule::getStatus, status);
        }
        if (StrUtil.isNotEmpty(attenceRuleType)) {
            wrapper.like(SyssetAttenceRule::getAttenceRuleType, attenceRuleType);
        }
        if (StrUtil.isNotEmpty(attenceRuleName)) {
            wrapper.like(SyssetAttenceRule::getAttenceRuleName, attenceRuleName);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SyssetAttenceRule getSyssetAttenceRuleById(Long id) {
        return getById(id);
    }

    @Override
    public int create(SyssetAttenceRule syssetAttenceRule) {
        return this.baseMapper.insert(syssetAttenceRule);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SyssetAttenceRule syssetAttenceRule) {
        syssetAttenceRule.setId(id);
        return this.baseMapper.updateById(syssetAttenceRule);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        SyssetAttenceRule syssetAttenceRule = new SyssetAttenceRule();
        syssetAttenceRule.setId(id);
        syssetAttenceRule.setStatus(status);
        return this.baseMapper.updateById(syssetAttenceRule);

    }

    @Override
    public SyssetAttenceRule getByAttenceRuleName(String attenceRuleName) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SyssetAttenceRule>().eq(SyssetAttenceRule::getAttenceRuleName, attenceRuleName));
    }

    @Override
    public List<SyssetAttenceRule> getListAll() {
        return list(new LambdaQueryWrapper<SyssetAttenceRule>().eq(SyssetAttenceRule::getStatus, CommonConstant.VALID_STATUS));
    }

    @Override
    public String getByAttenceRuleByTime(Date date) throws ParseException {
        String status = "2";
        SyssetAttenceRule syssetAttenceRule = this.baseMapper.getByAttenceRuleByTime(date);
        if (null != syssetAttenceRule) {
            status = "1";
        } else {
            syssetAttenceRule = this.baseMapper.selectById(1);
            if (syssetAttenceRule.getAttenceDay().contains(date.getDay() + "")) {
                if (DateTimeUtils.compare(date,new Date())==1) {
                    status = "2";
                }else{
                    status = "1";
                }
            } else {
                status = "1";
            }
        }
        return status;
    }
}
