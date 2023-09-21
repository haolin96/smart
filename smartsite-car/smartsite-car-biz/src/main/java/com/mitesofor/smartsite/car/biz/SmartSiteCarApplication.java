package com.mitesofor.smartsite.car.biz;

import com.matesofor.smartsite.common.swagger.annotation.EnableIntelligenceSwagger2;
import com.mitesofor.smartsite.common.feign.annotation.EnableIntelligenceFeignClients;
//import com.mitesofor.smartsite.common.job.annotation.EnableIntelligenceXxlJob;
import com.mitesofor.smartsite.common.security.annotation.EnableIntelligenceResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringCloudApplication
@MapperScan("com.mitesofor.smartsite.car.biz.mapper")
@EnableIntelligenceSwagger2
@EnableIntelligenceFeignClients
@EnableIntelligenceResourceServer
//@EnableIntelligenceXxlJob
@EnableScheduling
public class SmartSiteCarApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartSiteCarApplication.class, args);
    }
}
