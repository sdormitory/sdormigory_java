package cn.sdormitory.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnums {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;
}
