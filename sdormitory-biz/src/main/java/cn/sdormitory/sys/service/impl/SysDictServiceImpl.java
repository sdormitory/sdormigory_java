package cn.sdormitory.sys.service.impl;

import cn.sdormitory.common.exception.ApiException;
import cn.sdormitory.common.exption.SysExceptionEnum;
import cn.sdormitory.sys.dao.SysDictDao;
import cn.sdormitory.sys.entity.SysDict;
import cn.sdormitory.sys.service.SysDictService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 数据字典
 */
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictDao, SysDict> implements SysDictService {

    @Override
    public IPage<SysDict> getPage(SysDict sysDict, Integer pageSize, Integer pageNum) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>(sysDict);
        if (StrUtil.isNotEmpty(sysDict.getBeginTime())) {
            wrapper.gt(SysDict::getCreateTime, sysDict.getBeginTime());
        }
        if (StrUtil.isNotEmpty(sysDict.getEndTime())) {
            wrapper.lt(SysDict::getCreateTime, sysDict.getEndTime());
        }
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public int create(SysDict sysDict) {
        if (checkDictTypeUnique(null, sysDict.getType())) {
            throw new ApiException(SysExceptionEnum.DICT_TYPE_EXISTS);
        }
        return this.baseMapper.insert(sysDict);
    }


    @Override
    public int updateStatus(Long id, String status) {
        return 0;
    }

    @Override
    public int update(Long id, SysDict sysDict) {
        sysDict.setId(id);
        if (checkDictTypeUnique(id, sysDict.getType())) {
            throw new ApiException(SysExceptionEnum.DICT_TYPE_EXISTS);
        }
        return this.baseMapper.updateById(sysDict);
    }

    @Override
    public SysDict selectById(Long id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public List<SysDict> getExportList(SysDict sysDict) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>(sysDict);
        if (StrUtil.isNotEmpty(sysDict.getBeginTime())) {
            wrapper.gt(SysDict::getCreateTime, sysDict.getBeginTime());
        }
        if (StrUtil.isNotEmpty(sysDict.getEndTime())) {
            wrapper.lt(SysDict::getCreateTime, sysDict.getEndTime());
        }
        return list(wrapper);
    }


    /**
     * 查询字典类型是否存在
     *
     * @param id
     * @param type
     * @return
     */
    private boolean checkDictTypeUnique(Long id, String type) {
        Long dictId = id == null ? -1L : id;
        SysDict sysDict = this.baseMapper.selectOne(new LambdaQueryWrapper<SysDict>().eq(SysDict::getType, type));
        return sysDict != null && !dictId.equals(sysDict.getId());
    }
}