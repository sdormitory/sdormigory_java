package cn.sdormitory.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: SmsTypeEnums
 */
@Getter
@AllArgsConstructor
public enum SmsTypeEnums {
    /**
     * 单条信息
     */
    SINGLE(10, "单条短信"),
    /**
     * 群发信息
     */
    BATCH(20, "群发短信"),
    ;


    private Integer value;
    private String name;

    public static SmsTypeEnums getByType(int value) {
        for (SmsTypeEnums userType : values()) {
            if (userType.getValue().equals(value)) {
                return userType;
            }
        }
        return null;
    }
}
