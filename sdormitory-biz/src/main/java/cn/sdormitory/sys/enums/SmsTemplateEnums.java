package cn.sdormitory.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName: SmsTypeEnums
 */
@Getter
@AllArgsConstructor
public enum SmsTemplateEnums {
    /**
     * 注册验证码
     */
    REGISTER_VERIFICATION_CODE(1001, "cbec注册验证码"),
    UPDATE_PASSWORD_VERIFICATION_CODE(1002, "修改密码验证码"),
    ;


    private Integer value;
    private String name;

    public static SmsTemplateEnums getByType(int value) {
        for (SmsTemplateEnums userType : values()) {
            if (userType.getValue().equals(value)) {
                return userType;
            }
        }
        return null;
    }
}
