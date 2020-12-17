package cn.sdormitory.common.utils.poi;

import java.util.List;

/**
 * create By: Zhou Yinsen
 * date: 12/17/2020 11:50 AM
 * description:
 */
public class ParamCheck {

    private ParamCheck() {

    }

    /**
     * 校验参数是否为空
     *
     * @param args
     * @return
     */
    public static boolean checkParamIsEmpty(Object... args) {
        for (Object param : args) {
            if (param == null) {
                return true;
            }
            if (param instanceof String && "".equals(param)) {
                return true;
            }
            if (param instanceof Integer && 0 == (int) param) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验参数是否全部为空
     *
     * @param args
     * @return
     */
    public static boolean checkParamIsAllEmpty(Object... args) {
        for (Object param : args) {
            if (param != null) {
                boolean flag = false;
                if (param instanceof String && "".equals(param)) {
                    flag = true;
                }
                if (param instanceof Integer && 0 == (int) param) {
                    flag = true;
                }
                if (!flag) {
                    return false;
                }
            }

        }
        return true;
    }

    /**
     * 校验字符串是否为空
     * @param str
     * @return
     */
    public static boolean isStringEmpty(String str) {
        return str == null || "".equals(str);
    }

    /**
     * 校验数字是否为空
     * @return
     */
    public static boolean isIntEmpty(Integer number) {
        return number == null || number == 0;
    }

    /**
     * 校验集合是否为空
     * @return
     */
    public static boolean isListEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

}
