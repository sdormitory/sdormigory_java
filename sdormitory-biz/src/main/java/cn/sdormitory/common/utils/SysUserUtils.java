package cn.sdormitory.common.utils;

import cn.sdormitory.sys.entity.SysUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * ClassName: BeanUtils
 * Description: 获取当前登陆用户信息
 */
public class SysUserUtils {

    public static SysUser getSysUser() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            Authentication auth = ctx.getAuthentication();
            SysUserDetails memberDetails = (SysUserDetails) auth.getPrincipal();
            return memberDetails.getSysUser();
        } catch (Exception e) {
            return new SysUser();
        }
    }
}
