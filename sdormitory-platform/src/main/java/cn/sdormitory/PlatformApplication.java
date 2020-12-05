package cn.sdormitory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动类
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@MapperScan(basePackages = {"cn.sdormitory.sys.dao","cn.sdormitory.monitor.dao","cn.sdormitory.basedata.dao","cn.sdormitory.sysset.dao"})
@MapperScan(basePackages = {"cn.sdormitory.*.dao"})
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }




}