package cn.sdormitory.smartdor.dao;

import cn.sdormitory.smartdor.entity.SdLeave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/27 17:40
 * @version：V1.0
 */
@Mapper
public interface SdLeaveDao extends BaseMapper<SdLeave> {

    /**
     * 查询学生当天是否已有请假信息
     */
    Integer selectByTimeAndNo(@Param("time")String time,@Param("studentNo") String studentNo);

}
