package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门表
 */
@Mapper
public interface SysDeptDao extends BaseMapper<SysDept> {


    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    List<Integer> selectDeptListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据父级ID 获取子部门ID
     *
     * @param parentId
     * @return
     */
    List<Long> queryDeptIdList(@Param("parentId") Long parentId);
}
