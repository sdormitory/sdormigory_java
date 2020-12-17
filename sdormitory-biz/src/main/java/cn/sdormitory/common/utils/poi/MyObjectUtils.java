package cn.sdormitory.common.utils.poi;
import cn.sdormitory.common.api.CommonResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * create By: Zhou Yinsen
 * date: 12/17/2020 11:48 AM
 * description:
 */
public class MyObjectUtils {


    private MyObjectUtils() {

    }

    /**
     * 通过属性名称获取属性值
     *
     * @param object
     * //@param fieldName
     * @return
     */
    public static Object getFieldValueByName(Object object, Field field) {
        try {
            String fieldName = field.getName();
            StringBuilder sb = new StringBuilder();
            sb.append("get");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = object.getClass().getMethod(sb.toString());
            return method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("出错啦!");
        }
    }

    /**
     * 通过属性名称给对象赋值
     *
     * @param object
     * //@param fieldName
     * @param value
     * @return
     */
    public static Object setFieldValueByName(Object object, Field field, Object value) {
        try {
            String fieldName = field.getName();
            Class<?>[] paramTypes = new Class[1];
            paramTypes[0] = field.getType();
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            Method method = object.getClass().getMethod(sb.toString(), paramTypes);
            return method.invoke(object, value);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.failed("出错啦");
        }
    }

}
