package com.zhl.springboot.fdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author Lenovo
 * @title: FastDfsConfig
 * @projectName spring-boot-emo
 * @description: TODO
 * @date 2019/12/29 13:50
 */
@Configuration
@Import(FdfsClientConfig.class)
//JMX重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastDfsConfig {
}
