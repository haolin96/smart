package com.mitesofor.smartsite.car.biz.controller;

import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.CarMaterial;
import com.mitesofor.smartsite.car.biz.service.CarMaterialServcie;
import com.mitesofor.smartsite.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carMaterial")
@Api(value = "车辆物资信息", tags = "车辆物资进出信息管理", description = "统计车辆物资进出信息")
public class CarMaterialController {
    @Autowired
    CarMaterialServcie carMaterialServcie;
    @GetMapping
    @ApiOperation(value = "查询车辆物资信息", notes = "查询车辆物资信息")
    public R get(){
        try{
            List<CarMaterial> res = carMaterialServcie.getCarMaterial();
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @Scheduled(cron = "47 24,46 8,11,14,17 * * *")
    @Scheduled(cron = "24 18,37 9,13,17 * * *")
    @Scheduled(cron = "9 6,33 8,10,12,14,16,18 * * *")
    @Scheduled(cron = "58 20,54 7,12,15 * * *")
    @GetMapping("/produce")
    public R produce(){
        try{
            carMaterialServcie.produceData();
            return R.ok(1);
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }
}
