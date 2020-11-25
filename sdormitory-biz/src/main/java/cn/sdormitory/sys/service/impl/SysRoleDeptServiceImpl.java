package cn.sdormitory.sys.service.impl;

import cn.sdormitory.sys.dao.SysRoleDeptDao;
import cn.sdormitory.sys.entity.SysRoleDept;
import cn.sdormitory.sys.service.SysRoleDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色部门关联
 */
@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl extends ServiceImpl<SysRoleDeptDao, SysRoleDept> implements SysRoleDeptService {

    @Override
    public int deleteRoleDeptByRoleId(Long roleId) {
        return this.baseMapper.delete(new LambdaQueryWrapper<SysRoleDept>()
                .eq(SysRoleDept::getRoleId, roleId));
    }

    @Override
    public int batchRoleDept(List<SysRoleDept> list) {
        return saveBatch(list) ? 1 : 0;
    }

    @Override
    public List<Long> queryDeptIdList(List<Long> roleIdList) {
        return baseMapper.queryDeptIdList(roleIdList);
    }
}