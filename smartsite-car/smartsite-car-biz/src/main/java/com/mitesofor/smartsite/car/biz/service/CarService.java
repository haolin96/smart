package com.mitesofor.smartsite.car.biz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.car.api.dto.CarAndDriverDto;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.vo.CarAndDriverVo;

public interface CarService extends IService<Car> {


    boolean save(CarAndDriverDto carAndDriverDto);

    boolean update(Car car);

    boolean removeById(Integer id);
    Car load(Car car);

    Car getById(Integer id);

    Page<CarAndDriverVo> getCarListByPage(Long current, Long size, Car car);
    /**
     * 统计在册车辆
     * @return
     */
    Integer countRegistered();


}
