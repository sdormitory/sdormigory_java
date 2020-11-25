package cn.sdormitory.sys.service.impl;

import cn.sdormitory.sys.dao.SysDictDetailDao;
import cn.sdormitory.sys.entity.SysDictDetail;
import cn.sdormitory.sys.enums.StatusEnums;
import cn.sdormitory.sys.service.SysDictDetailService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 数据字典详情
 */
@Service("sysDictDetailService")
public class SysDictDetailServiceImpl extends ServiceImpl<SysDictDetailDao, SysDictDetail> implements SysDictDetailService {


    @Override
    public List<SysDictDetail> selectDictDataByType(String dictType) {
        return list(new LambdaQueryWrapper<SysDictDetail>()
                .eq(SysDictDetail::getDictType, dictType)
                .eq(SysDictDetail::getStatus, StatusEnums.ENABLE.getKey()));
    }

    @Override
    public IPage<SysDictDetail> getPage(SysDictDetail dictDetail, Integer pageSize, Integer pageNum) {
        return page(new Page<>(pageNum, pageSize), new LambdaQueryWrapper<>(dictDetail));
    }

    @Override
    public SysDictDetail selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int create(SysDictDetail dictDetail) {
        return this.baseMapper.insert(dictDetail);
    }

    @Override
    public int updateStatus(Long id, String status) {
        SysDictDetail sysDictDetail = new SysDictDetail();
        sysDictDetail.setId(id);
        sysDictDetail.setStatus(status);
        return this.baseMapper.updateById(sysDictDetail);
    }

    @Override
    public int update(Long id, SysDictDetail dictDetail) {
        dictDetail.setId(id);
        return this.baseMapper.updateById(dictDetail);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<SysDictDetail> getExportList(SysDictDetail dictDetail) {
        return list(new LambdaQueryWrapper<>(dictDetail));
    }
}