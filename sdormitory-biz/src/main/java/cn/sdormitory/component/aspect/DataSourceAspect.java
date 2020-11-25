package cn.sdormitory.component.aspect;

import cn.sdormitory.common.annotation.DataSource;
import cn.sdormitory.common.datasource.DynamicDataSourceContextHolder;
import cn.sdormitory.common.utils.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ClassName: DataScopeAspect
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect {
    @Pointcut("@annotation(cn.sdormitory.common.annotation.DataSource)"
            + "|| @within(cn.sdormitory.common.annotation.DataSource)")
    public void dsPointCut() {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);

        if (StringUtils.isNotNull(dataSource)) {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.value().name());
        }

        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (StringUtils.isNotNull(targetDataSource)) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            return method.getAnnotation(DataSource.class);
        }
    }
}
