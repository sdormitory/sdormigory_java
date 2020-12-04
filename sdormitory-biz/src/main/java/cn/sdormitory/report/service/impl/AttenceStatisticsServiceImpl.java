package cn.sdormitory.report.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.report.dao.AttenceStatisticsDao;
import cn.sdormitory.report.service.AttenceStatisticsService;
import cn.sdormitory.report.vo.AttenceStatisticsVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/3 19:11
 * @version：V1.0
 */
@Slf4j
@Service("attenceStatisticsService")
public class AttenceStatisticsServiceImpl extends ServiceImpl<AttenceStatisticsDao, AttenceStatisticsVO> implements AttenceStatisticsService {
    @Resource
    AttenceStatisticsDao attenceStatisticsDao;

    @Override
    public List<AttenceStatisticsVO> getAttenceStaList(String accDate,int pageNum,int pageSize) {
        int currIndex=(pageNum-1)*pageSize;
        return attenceStatisticsDao.getAttenceStaList(accDate,currIndex,pageSize);
    }
}
