package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysRoleDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色部门关联
 *
 */
@Mapper
public interface SysRoleDeptDao extends BaseMapper<SysRoleDept> {

    /**
     * 根据角色ID，获取部门ID列表
     *
     * @param roleIdList
     * @return
     */
    List<Long> queryDeptIdList(@Param("roleIdList") List<Long> roleIdList);
}
