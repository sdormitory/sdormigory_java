package cn.sdormitory.sys.service.impl;

import cn.sdormitory.sys.dao.SysUserRoleDao;
import cn.sdormitory.sys.entity.SysUserRole;
import cn.sdormitory.sys.service.SysUserRoleService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户和角色关系表
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRole> implements SysUserRoleService {

    @Override
    public List<Long> listRoleIdByUserId(Long userId) {
        List<Long> ids = new ArrayList<>();
        List<SysUserRole> sysUserRoles = this.baseMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        if (CollectionUtil.isNotEmpty(sysUserRoles)) {
            sysUserRoles.forEach(item -> {
                ids.add(item.getRoleId());
            });
            return ids;
        }
        return null;
    }

    @Override
    public void insertUserAndUserRole(Long userId, List<Long> roleIdList) {
        this.baseMapper.insertUserAndUserRole(userId, roleIdList);
    }

    @Override
    public void delAndCreateRole(Long userId, List<Long> roleIdList) {
        remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId));
        if (CollUtil.isNotEmpty(roleIdList)) {
            //保存用户与角色关系
            insertUserAndUserRole(userId, roleIdList);
        }
    }

    @Override
    public List<Integer> listDataScopesByUserId(Long userId) {
        return this.baseMapper.listDataScopesByUserId(userId);
    }

}