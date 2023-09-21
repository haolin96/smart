package com.mitesofor.smartsite.warehouse.biz;

import com.matesofor.smartsite.common.swagger.annotation.EnableIntelligenceSwagger2;
import com.mitesofor.smartsite.common.feign.annotation.EnableIntelligenceFeignClients;
import com.mitesofor.smartsite.common.job.annotation.EnableIntelligenceXxlJob;
import com.mitesofor.smartsite.common.security.annotation.EnableIntelligenceResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 设备管理系统
 */
@EnableIntelligenceSwagger2
@SpringCloudApplication
@EnableIntelligenceFeignClients
@EnableIntelligenceResourceServer
@EnableIntelligenceXxlJob
public class SmartsiteWarehouseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartsiteWarehouseApplication.class, args);
	}

}
