package cn.sdormitory.smartdor.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.smartdor.dao.SdHygieneDao;
import cn.sdormitory.smartdor.entity.SdHygiene;
import cn.sdormitory.smartdor.service.HygieneDeductService;
import cn.sdormitory.smartdor.service.SdHygieneService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:17
 * @version：V1.0
 */
@Slf4j
@Service("sdHygieneService")
public class SdHygieneServiceImpl extends ServiceImpl<SdHygieneDao, SdHygiene> implements SdHygieneService {
    @Resource
    HygieneDeductService hygieneDeductService;
    @Override
    public IPage<SdHygiene> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String checkDate = (String) params.get("checkDate");

        LambdaQueryWrapper<SdHygiene> wrapper = new LambdaQueryWrapper<>();


        if (StrUtil.isNotEmpty(checkDate)) {
            wrapper.eq(SdHygiene::getCheckDate, checkDate);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public SdHygiene getSdHygieneById(Long id) {
        return getById(id);
    }

    @Override
    public int create(SdHygiene sdHygiene) {
        return this.baseMapper.insert(sdHygiene);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, SdHygiene sdHygiene) {
        sdHygiene.setId(id);
        return this.baseMapper.updateById(sdHygiene);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public SdHygiene getByCheckDateAndBdId(String checkDate, Long bdormitoryId) {
        LambdaQueryWrapper<SdHygiene> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SdHygiene::getCheckDate, checkDate);
        wrapper.eq(SdHygiene::getBdormitoryId, bdormitoryId);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int insertSdHygieneAndDeduct(SdHygiene sdHygiene) {
        int result = this.baseMapper.insert(sdHygiene);
        //保存卫生与卫生项关系
        hygieneDeductService.insertHygieneAndHygieneDeduct(sdHygiene.getId(),sdHygiene.getDeductIds());
        return result;
    }

    @Override
    public int updateSdHygieneAndDeduct(SdHygiene sdHygiene) {
        //先删除卫生与卫生项关系
        hygieneDeductService.delAndCreateHygiene(sdHygiene.getId(), sdHygiene.getDeductIds());
        // 更新卫生信息
        return this.baseMapper.updateById(sdHygiene);
    }
}
