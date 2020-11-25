package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 数据字典
 */
@Mapper
public interface SysDictDao extends BaseMapper<SysDict> {
	
}
