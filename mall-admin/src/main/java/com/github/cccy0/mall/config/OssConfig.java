package com.github.cccy0.mall.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhai
 * 2020/9/6 17:44
 */
@Configuration
public class OssConfig {
    @Value("${settings.aliyun.oss.endpoint}")
    private String aliyunOssEndPoint;

    @Value("${settings.aliyun.oss.accessKeyId}")
    private String aliyunOssAccessKeyId;

    @Value("${settings.aliyun.oss.accessKeySecret}")
    private String aliyunOssAccessKeySecret;

    @Bean
    public OSSClient ossClient() {
        return new OSSClient(aliyunOssEndPoint,aliyunOssAccessKeyId,aliyunOssAccessKeySecret);
//        return new OSSClientBuilder().build(aliyunOssEndPoint, aliyunOssAccessKeyId, aliyunOssAccessKeySecret);
    }
}
