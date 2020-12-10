package cn.sdormitory.basedata.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.basedata.dao.BDormitoryDao;
import cn.sdormitory.basedata.entity.BDormitory;
import cn.sdormitory.basedata.service.BDormitoryService;
import cn.sdormitory.common.constant.CommonConstant;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/7 20:22
 * @version：V1.0
 */
@Slf4j
@Service("bDormitoryService")
public class BDormitoryServiceImpl extends ServiceImpl<BDormitoryDao, BDormitory> implements BDormitoryService {
    @Resource
    private BDormitoryDao bDormitoryDao;

    @Override
    public IPage<BDormitory> getPage(Map<String, Object> params) {
        int pageSize = Integer.parseInt(String.valueOf(params.get("pageSize")));
        int pageNum = Integer.parseInt(String.valueOf(params.get("pageNum")));

        String buildingNo = (String) params.get("buildingNo");
        String storey = (String) params.get("storey");
        String dormitoryNo = (String) params.get("dormitoryNo");
        String status = (String) params.get("status");

        LambdaQueryWrapper<BDormitory> wrapper = new LambdaQueryWrapper<>();

        if (StrUtil.isNotEmpty(buildingNo)) {
            wrapper.eq(BDormitory::getBuildingNo, buildingNo);
        }
        if (StrUtil.isNotEmpty(storey)) {
            wrapper.eq(BDormitory::getStorey, storey);
        }
        if (StrUtil.isNotEmpty(dormitoryNo)) {
            wrapper.eq(BDormitory::getDormitoryNo, dormitoryNo);
        }
        if (StrUtil.isNotEmpty(status)) {
            wrapper.eq(BDormitory::getStatus, status);
        }

        wrapper.apply(params.get(CommonConstant.SQL_FILTER) != null, (String) params.get(CommonConstant.SQL_FILTER));
        return page(new Page<>(pageNum, pageSize), wrapper);
    }

    @Override
    public BDormitory getBDormitoryById(Long id) {
        return getById(id);
    }

    @Override
    public int create(BDormitory bDormitory) {
        return this.baseMapper.insert(bDormitory);
    }

    @Override
    public int delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

    @Override
    public int update(Long id, BDormitory bDormitory) {
        bDormitory.setId(id);
        return this.baseMapper.updateById(bDormitory);
    }

    @Override
    public int deleteByIds(Long[] ids) {
        return this.baseMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    public int updateStatus(Long id, String status) {
        BDormitory bDormitory = new BDormitory();
        bDormitory.setId(id);
        bDormitory.setStatus(status);
        return this.baseMapper.updateById(bDormitory);
    }

    @Override
    public BDormitory getByDormitoryInfo(String buildingNo, String storey, String dormitoryNo) {
        LambdaQueryWrapper<BDormitory> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BDormitory::getBuildingNo, buildingNo);
        wrapper.eq(BDormitory::getStorey, storey);
        wrapper.eq(BDormitory::getDormitoryNo, dormitoryNo);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public List<BDormitory> getListAll() {
        return list(new LambdaQueryWrapper<BDormitory>());
    }

    @Override
    public List<BDormitory> getBDormitoryListByCol(String buildingNo, String storey) {
        return bDormitoryDao.getBDormitoryListByCol(buildingNo,storey);
    }

  
}
