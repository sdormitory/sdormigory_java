package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 后台用户角色表
 */
@Mapper
public interface SysRoleDao extends BaseMapper<SysRole> {
	
}
