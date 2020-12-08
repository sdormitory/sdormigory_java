package cn.sdormitory.smartdor.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.sdormitory.smartdor.dao.HygieneDeductDao;
import cn.sdormitory.smartdor.entity.HygieneDeduct;
import cn.sdormitory.smartdor.service.HygieneDeductService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/20 14:48
 * @version：V1.0
 */
@Slf4j
@Service("userDeviceService")
public class HygieneDeductServiceImpl extends ServiceImpl<HygieneDeductDao, HygieneDeduct> implements HygieneDeductService {


    @Override
    public List<Long> listDeductIdByHygieneId(Long hygieneId) {
        List<Long> ids = new ArrayList<>();
        List<HygieneDeduct> hygieneDeducts = this.baseMapper.selectList(new LambdaQueryWrapper<HygieneDeduct>().eq(HygieneDeduct::getHygieneId, hygieneId));
        if (CollectionUtil.isNotEmpty(hygieneDeducts)) {
            hygieneDeducts.forEach(item -> {
                ids.add(item.getDeductId());
            });
            return ids;
        }
        return null;
    }

    @Override
    public void insertHygieneAndHygieneDeduct(Long hygieneId, List<Long> deductIdList) {
        this.baseMapper.insertHygieneAndHygieneDeduct(hygieneId, deductIdList);
    }

    @Override
    public void delAndCreateHygiene(Long hygieneId, List<Long> deductIdList) {
        remove(new LambdaQueryWrapper<HygieneDeduct>().eq(HygieneDeduct::getHygieneId, hygieneId));
        if (CollUtil.isNotEmpty(deductIdList)) {
            //保存用户与设备关系
            insertHygieneAndHygieneDeduct(hygieneId, deductIdList);
        }
    }
}
