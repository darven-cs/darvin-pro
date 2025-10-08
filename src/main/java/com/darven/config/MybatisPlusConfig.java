package com.darven.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Darven
 * @createTime: 2025-10-08  15:29
 * @description: TODO
 */
@Configuration
@MapperScan("com.darven.model.mapper")
public class MybatisPlusConfig {
}
