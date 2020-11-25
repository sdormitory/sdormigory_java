package cn.sdormitory.sys.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ClassName: SysConfigResult
 */
@Data
public class SysConfigResult {
    /**
     * key
     */
    @ApiModelProperty(value = "key")
    private String paramKey;
    /**
     * name
     */
    @ApiModelProperty(value = "name")
    private String paramName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

}
