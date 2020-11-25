package cn.sdormitory.sys.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色部门关联
 */
@Data
@TableName("sys_role_dept")
public class SysRoleDept implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     *
     */
    @ApiModelProperty(value = "roleId")
    private Long roleId;
    /**
     *
     */
    @ApiModelProperty(value = "deptId")
    private Long deptId;

}
