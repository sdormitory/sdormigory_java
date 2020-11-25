package cn.sdormitory.sysset.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.sysset.dao.SyssetHygieneDeductDao;
import cn.sdormitory.sysset.entity.SyssetHygieneDeduct;
import cn.sdormitory.sysset.service.SyssetHygieneDeductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/19 16:53
 * @version：V1.0
 */
@Slf4j
@Service("syssetHygieneDeductService")
public class SyssetHygieneDeductServiceImpl extends ServiceImpl<SyssetHygieneDeductDao, SyssetHygieneDeduct> implements SyssetHygieneDeductService {
    @Override
    public IPage<SyssetHygieneDeduct> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String deductOption = (String) params.get("deductOption");
        String status = (String) params.get("status");

        LambdaQueryWrapper<SyssetHygieneDeduct> wrapper = new LambdaQueryWrapper<>();


        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(SyssetHygieneDeduct::getStatus, status);
        }
        if (StrUtil.isNotEmpty(deductOption)) {
            wrapper.like(SyssetHygieneDeduct::getDeductOption, deductOption);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SyssetHygieneDeduct getSyssetHygieneDeductById(Long id) {
        return getById(id);
    }

    @Override
    public int create(SyssetHygieneDeduct syssetHygieneDeduct) {
        return this.baseMapper.insert(syssetHygieneDeduct);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SyssetHygieneDeduct syssetHygieneDeduct) {
        syssetHygieneDeduct.setId(id);
        return this.baseMapper.updateById(syssetHygieneDeduct);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        SyssetHygieneDeduct syssetHygieneDeduct= new SyssetHygieneDeduct();
        syssetHygieneDeduct.setId(id);
        syssetHygieneDeduct.setStatus(status);
        return this.baseMapper.updateById(syssetHygieneDeduct);
    }

    @Override
    public SyssetHygieneDeduct getByDeductOption(String deductOption) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SyssetHygieneDeduct>().eq(SyssetHygieneDeduct::getDeductOption, deductOption));
    }

    @Override
    public List<SyssetHygieneDeduct> getListAll() {
        return list(new LambdaQueryWrapper<SyssetHygieneDeduct>().eq(SyssetHygieneDeduct::getStatus,CommonConstant.VALID_STATUS));
    }
}
