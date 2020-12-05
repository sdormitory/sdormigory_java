package cn.sdormitory.report.service;

import cn.sdormitory.report.vo.AttenceStatisticsVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/3 19:10
 * @version：V1.0
 */
public interface AttenceStatisticsService extends IService<AttenceStatisticsVO> {
    List<AttenceStatisticsVO> getAttenceStaList(String accDate,int pageNum,int pageSize);
}
