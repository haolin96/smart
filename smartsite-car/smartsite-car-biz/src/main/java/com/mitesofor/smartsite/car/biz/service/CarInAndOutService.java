package com.mitesofor.smartsite.car.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.car.api.dto.CarInAndOutDto;
import com.mitesofor.smartsite.car.api.entity.CarInAndOut;
import com.mitesofor.smartsite.car.api.vo.CarInAndOutVo;

import java.util.Map;

public interface CarInAndOutService extends IService<CarInAndOut> {

    boolean save(CarInAndOutDto carInAndOutDto);

    boolean update(CarInAndOut carInAndOut);

    boolean removeById(Integer id);

    CarInAndOut getById(Integer id);

    Page<CarInAndOutVo> getListByPage(Long current, Long size, CarInAndOutDto carInAndOutDto);

    Page<CarInAndOutVo> getListByPageToday(Long current, Long size, CarInAndOutDto carInAndOutDto);
//    Page<CarInAndOutVO> getListByPageToday(Long current, Long size, CarInAndOut carInAndOut);
    /**
     * 统计今天出入车辆数
     *
     * @param
     * @return
     */
    Map<String, Long> countInAndOutToday();


    /**
     * 按小时统计今天车辆进出和在场车辆数
     * @return
     */
    Map<Integer, Map<String, Long>> CountInAndOutByHour();


    Map<String, Map<String, Long>> countInAndOutBySevenDays();

//    boolean checkRegistered(String carId);

    CarInAndOutVo getLatestIn();

    CarInAndOutVo getLatestOut();

    Long countUnRegisteredInToday();

}
