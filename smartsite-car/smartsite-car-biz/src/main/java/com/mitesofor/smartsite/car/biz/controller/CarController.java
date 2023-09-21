package com.mitesofor.smartsite.car.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.car.api.dto.CarAndDriverDto;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.vo.CarAndDriverVo;
import com.mitesofor.smartsite.car.biz.service.CarService;
import com.mitesofor.smartsite.car.biz.service.DriverService;
import com.mitesofor.smartsite.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@Api(value = "车辆信息", tags = "车辆信息管理", description = "统计车辆信息")
public class CarController {

    @Autowired
    public CarService carService;

    @Autowired
    public DriverService driverService;

    /**
     * 添加车辆信息
     * @param carAndDriverDto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "添加车辆信息", notes = "添加车辆信息")
    public R save(@RequestBody CarAndDriverDto carAndDriverDto){
        try{
            boolean res = carService.save(carAndDriverDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    /**
     * 修改车辆信息
     * @param car
     * @return
     */
    @PutMapping
    @ApiOperation(value = "修改车辆信息", notes = "修改车辆信息")
    public R update(@RequestBody Car car){
        try{
            boolean res = carService.update(car);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    /**
     * 根据id车牌号删除车辆信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据Id删除车辆信息", notes = "根据Id删除车辆信息")
    public R removeByCarId(@PathVariable Integer id){
        try{
            boolean res = carService.removeById(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    /**
     * 根据carId查询车辆信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据Id查询车辆信息", notes = "根据Id查询车辆信息")
    public R getById(@PathVariable Integer id){
        try{
            Car res = carService.getById(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    /**
     * 分页查询
     * @param current
     * @param size
     * @param car
     * @return
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询", notes = "分页查询车辆列表")
    public R getCarListByPage(Long current, Long size, Car car){
        try{
            Page<CarAndDriverVo> res = carService.getCarListByPage(current, size, car);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

}
