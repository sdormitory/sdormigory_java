package cn.sdormitory.sys.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysUserUpdatePasswordParam
 * Description: 修改密码参数
 */
@Data
public class SysUserUpdatePasswordParam {
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
