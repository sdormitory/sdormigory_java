package cn.sdormitory.sys.service;

import cn.sdormitory.sys.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 后台用户和角色关系表
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID，获取角色ID列表
     *
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Long> listRoleIdByUserId(Long userId);

    /**
     * 根据用户id 批量添加用户角色关系
     * @param userId
     * @param roleIdList
     */
    void insertUserAndUserRole(Long userId, List<Long> roleIdList);

    /**
     * 删除历史角色 创建新的角色
     * @param userId
     * @param roleIdList
     * @return
     */
    void delAndCreateRole(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID 获取权限标识
     * @param id
     * @return
     */
    List<Integer> listDataScopesByUserId(Long id);
}

