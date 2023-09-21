package com.mitesofor.smartsite.person.biz;

import com.matesofor.smartsite.common.swagger.annotation.EnableIntelligenceSwagger2;
import com.mitesofor.smartsite.common.feign.annotation.EnableIntelligenceFeignClients;
import com.mitesofor.smartsite.common.security.annotation.EnableIntelligenceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@MapperScan("com.mitesofor.smartsite.person.biz.mapper")
@EnableIntelligenceSwagger2
@EnableIntelligenceFeignClients
@EnableIntelligenceResourceServer
public class SmartsitePersonApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(SmartsitePersonApplication.class, args);
    }
}
