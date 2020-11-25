package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysSmsLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 短信日志表
 */
@Mapper
public interface SysSmsLogDao extends BaseMapper<SysSmsLog> {
	
}
