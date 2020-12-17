package cn.sdormitory.common.utils.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create By: Zhou Yinsen
 * date: 12/17/2020 11:45 AM
 * description: 字段注解效验
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface ExcelTitle {

    String title();

    String dataMap() default "";

}
