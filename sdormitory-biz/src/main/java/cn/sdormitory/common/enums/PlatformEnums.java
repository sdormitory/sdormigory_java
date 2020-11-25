package cn.sdormitory.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: PlatformEnums
 */
@Getter
@AllArgsConstructor
public enum PlatformEnums {
    // 平台来源
    PC(1, "PC"),

    WX_MINIAPP(2, "微信小程序"),

    APP(3, "APP"),

    H5(4, "H5"),
    ;

    private Integer code;
    private String msg;
}
