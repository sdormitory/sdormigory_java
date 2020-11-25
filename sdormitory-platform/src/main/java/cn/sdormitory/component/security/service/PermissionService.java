package cn.sdormitory.component.security.service;

import cn.sdormitory.common.utils.SecurityUtils;
import cn.sdormitory.common.utils.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ClassName: PermissionService
 */
@Service("ss")
public class PermissionService {
    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    public Boolean hasPermi(String... permissions) {
        if (StringUtils.isEmpty(permissions)) {
            return false;
        }
        // 获取当前用户的所有权限
        List<String> harryPermissions = SecurityUtils.getUserDetails().getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        // 判断当前用户的所有权限是否包含接口上定义的权限
        return harryPermissions.contains(ALL_PERMISSION) || Arrays.stream(permissions).anyMatch(harryPermissions::contains);
    }
}
