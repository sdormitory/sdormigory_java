package cn.sdormitory.sys.dao;

import cn.sdormitory.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户权限表
 */
@Mapper
public interface SysMenuDao extends BaseMapper<SysMenu> {


    /**
     * 获取用户所有权限
     *
     * @param userId
     * @return
     */
    List<SysMenu> getPermissionList(@Param("userId") Long userId);

    /**
     * 查询用户的所有菜单ID
     *
     * @param userId 用户id
     * @return 该用户所有可用的菜单
     */
    List<SysMenu> listMenuByUserId(@Param("userId") Long userId);

    /**
     * 根据用户ID查询菜单
     *
     * @param userId
     * @return
     */
    List<SysMenu> selectMenuTreeByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID查询菜单树信息
     *
     * @param roleId 角色ID
     * @return 选中菜单列表
     */
    List<Integer> selectMenuListByRoleId(@Param("roleId") Long roleId);
}
