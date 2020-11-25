package cn.sdormitory.component.security.service;

import cn.sdormitory.common.exception.ApiException;
import cn.sdormitory.common.exption.SysExceptionEnum;
import cn.sdormitory.common.utils.BeanUtils;
import cn.sdormitory.common.utils.SysUserDetails;
import cn.sdormitory.sys.entity.SysMenu;
import cn.sdormitory.sys.entity.SysUser;
import cn.sdormitory.sys.service.SysMenuService;
import cn.sdormitory.sys.service.SysUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: UserDetailsServiceImpl
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysMenuService sysMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取登录用户信息
        SysUser user = sysUserService.getByUserName(username);
        if (BeanUtils.isNull(user)) {
            throw new ApiException(SysExceptionEnum.WRONG_USERNAME_OR_PASSWORD);
        }

        List<SysMenu> permissions = sysMenuService.selectMenuList(user.getId());
        return new SysUserDetails(user, permissions);
    }
}
