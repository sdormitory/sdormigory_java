package cn.sdormitory.report.dao;

import cn.sdormitory.report.vo.AttenceStatisticsVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @创建人：zhouyang
 * @创建时间：2020/12/3 16:07
 * @version：V1.0
 */
@Mapper
public interface AttenceStatisticsDao extends BaseMapper<AttenceStatisticsVO> {
   List<AttenceStatisticsVO> getAttenceStaList(@Param("accDate") String accDate,@Param("currIndex")int currIndex,@Param("pageSize")int pageSize);
}
