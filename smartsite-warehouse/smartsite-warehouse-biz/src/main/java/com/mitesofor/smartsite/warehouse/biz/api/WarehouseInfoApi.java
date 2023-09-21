package com.mitesofor.smartsite.warehouse.biz.api;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 仓库信息api
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/warehouse")
@Api(value = "warehouse", tags = "仓库信息api")
public class WarehouseInfoApi {

}
