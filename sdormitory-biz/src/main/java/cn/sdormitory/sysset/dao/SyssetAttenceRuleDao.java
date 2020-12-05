package cn.sdormitory.sysset.dao;

import cn.sdormitory.sysset.entity.SyssetAttenceRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @创建人：zhouyang
 * @创建时间：2020/11/26 16:07
 * @version：V1.0
 */
@Mapper
public interface SyssetAttenceRuleDao extends BaseMapper<SyssetAttenceRule> {

    /**
     * 根据当前时间获取考勤信息
     */
    SyssetAttenceRule getByAttenceRuleByTime(@Param("date") Date date);
}
