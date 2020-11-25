package cn.sdormitory.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ClassName: SmsConfig
 */
@Data
@Component
@ConfigurationProperties(prefix = "harry.sms")
public class SmsConfig {

    private String accessKeyId;

    private String accessKeySecret;

    private boolean enabled;
}
