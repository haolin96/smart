package com.mitesofor.smartsite.car.biz.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.car.api.dto.CarInAndOutDto;
import com.mitesofor.smartsite.car.api.entity.CarInAndOut;
import com.mitesofor.smartsite.car.api.vo.CarInAndOutVo;
import com.mitesofor.smartsite.car.biz.service.CarInAndOutService;
import com.mitesofor.smartsite.car.biz.service.CarService;
import com.mitesofor.smartsite.common.core.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/carInAndOut")
@Api(value = "车辆进出信息", tags = "车辆进出信息管理", description = "统计车辆进出信息")
public class CarInAndOutController {

    @Autowired
    public CarInAndOutService carInAndOutService;

    @Autowired
    public CarService carService;


    @ApiOperation(value = "添加车辆进出信息", notes = "添加车辆进出信息")
    @PostMapping
    public R save(@RequestBody CarInAndOutDto carInAndOutDto){
        try{
            boolean res = carInAndOutService.save(carInAndOutDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "修改车辆进出信息", notes = "修改车辆进出信息")
    @PutMapping
    public R update(@RequestBody CarInAndOut carInAndOut) {
        try{
            boolean res = carInAndOutService.update(carInAndOut);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "删除车辆进出信息", notes = "删除车辆进出信息")
    @DeleteMapping("/{id}")
    public R removeById(@PathVariable Integer id) {
        try{
            boolean res = carInAndOutService.removeById(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }
    @ApiOperation(value = "查询车辆进出信息(根据id)", notes = "修改车辆进出信息(根据id)")
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        try{
            CarInAndOut res = carInAndOutService.getById(id);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "查询车辆进出信息(分页)", notes = "查询车辆进出信息(分页)")
    @GetMapping("/page")
    public R getListByPage(Long current, Long size, CarInAndOutDto carInAndOutDto) {
        try{
            Page<CarInAndOutVo> res = carInAndOutService.getListByPage(current, size, carInAndOutDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "查询今天车辆进出信息(分页)", notes = "查询今天车辆进出信息(分页)")
    @GetMapping("/pageToday")
    public R getListByPageToday(Long current, Long size, CarInAndOutDto carInAndOutDto) {
        try{
            Page<CarInAndOutVo> res = carInAndOutService.getListByPageToday(current, size, carInAndOutDto);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }



    /**
     * 统计在册车辆数，今天进出和在场车辆数，今天非注册车辆进场数
     * @param
     * @return
     */
    @ApiOperation(value = "统计在册车辆数，今天进出和在场车辆数，今天非注册车辆进场数", notes = "统计在册车辆数，今天进出和在场车辆数，今天非注册车辆进场数")
    @GetMapping("/countInAndOutToday")
    public R countInAndOutToday() {
        try {
            Map<String, Long> res = carInAndOutService.countInAndOutToday();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 按小时统计车辆进出和在场车辆数
     * @return
     */
    @ApiOperation(value = "按小时统计车辆进出和在场车辆数", notes = "按小时统计车辆进出和在场车辆数")
    @GetMapping("/countInAndOutByHour")
    public R countInAndOutByHour() {
        try {
            Map<Integer, Map<String, Long>> res = carInAndOutService.CountInAndOutByHour();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 统计七天内车辆进出信息
     */
    @ApiOperation(value = "统计七天内车辆进出信息", notes = "统计七天内车辆进出信息")
    @GetMapping("countInAndOutBySevenDays")
    public R countInAndOutBySevenDays() {
        try {
            Map<String, Map<String, Long>> res = carInAndOutService.countInAndOutBySevenDays();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 统计在册车辆
     * @return
     */
    @ApiOperation(value = "统计在册车辆", notes = "统计在册车辆")
    @GetMapping("/countRegistered")
    public R countRegistered() {
        try {
            Integer res = carService.countRegistered();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

//    /**
//     * 检查是否是注册车辆
//     * @param carInAndOut
//     * @return
//     */
//    @ApiOperation(value = "检查是否是注册车辆", notes = "检查是否是注册车辆")
//    @GetMapping("/checkRegistered")
//    public R checkRegistered(@RequestBody CarInAndOut carInAndOut) {
//        Assert.notBlank(carInAndOut.getCarId(), "请输入车牌号");
//        try {
//            boolean res = carInAndOutService.checkRegistered(carInAndOut.getCarId());
//            return R.ok(res, "success");
//        } catch(Exception e) {
//            return R.failed(e, "failed");
//        }
//    }

    /**
     * 查询最新注册车辆进出信息
     * @return
     */
    @ApiOperation(value = "查询最新车辆入场信息", notes = "查询最新车辆入场信息")
    @GetMapping("/LatestIn")
    public R getLatestIn() {
        try {
            CarInAndOutVo res = carInAndOutService.getLatestIn();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 查询最新非注册车辆进出信息
     * @return
     */
    @ApiOperation(value = "查询最新车辆出场信息", notes = "查询最新车辆出场信息")
    @GetMapping("/LatestOut")
    public R getLatestOut() {
        try {
            CarInAndOutVo res = carInAndOutService.getLatestOut();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }

    /**
     * 查询今天非注册车辆进场信息
     * @return
     */
    @ApiOperation(value = "统计今天非注册车辆进场信息", notes = "统计今天非注册车辆进场信息")
    @GetMapping("/countUnRegisteredInToday")
    public R countUnRegisteredInToday() {
        try {
            Long res = carInAndOutService.countUnRegisteredInToday();
            return R.ok(res, "success");
        } catch(Exception e) {
            return R.failed(e, "failed");
        }
    }
}
