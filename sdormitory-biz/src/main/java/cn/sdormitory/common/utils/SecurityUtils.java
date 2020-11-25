package cn.sdormitory.common.utils;

import cn.sdormitory.common.constant.CommonConstant;
import cn.sdormitory.common.exception.ApiException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * ClassName: SecurityUtils
 * Description: 安全服务工具类
 */
public class SecurityUtils {

    public static UserDetails getUserDetails() {
        UserDetails userDetails;
        try {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new ApiException("登录状态过期", 401);
        }
        return userDetails;
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && CommonConstant.SUPER_ADMIN_ID == userId;
    }
}
