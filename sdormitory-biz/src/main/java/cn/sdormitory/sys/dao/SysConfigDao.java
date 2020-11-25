package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysConfig;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统配置信息表/枚举值表
 */
@Mapper
public interface SysConfigDao extends BaseMapper<SysConfig> {
	
}
