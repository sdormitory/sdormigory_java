package cn.sdormitory.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: StatusEnums
 */
@Getter
@AllArgsConstructor
public enum StatusEnums {
    /**
     * 禁用
     */
    DISABLE("0", "禁用"),
    /**
     * 启用
     */
    ENABLE("1", "启用"),
    ;


    private String key;
    private String name;
}
