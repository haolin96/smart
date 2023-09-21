package com.mitesofor.smartsite.car.biz.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.car.api.dto.CarAndDriverDto;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.Driver;
import com.mitesofor.smartsite.car.api.vo.CarAndDriverVo;
import com.mitesofor.smartsite.car.biz.mapper.CarMapper;
import com.mitesofor.smartsite.car.biz.mapper.DriverMapper;
import com.mitesofor.smartsite.car.biz.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Autowired
    public CarMapper carMapper;

    @Autowired
    public DriverMapper driverMapper;
    @Override
    public boolean save(CarAndDriverDto carAndDriverDto) {
        carAndDriverDto.setIsDelete(0);
        carAndDriverDto.setCreateTime(new Date());
        Car car = new Car();
        Driver driver = new Driver();

        BeanUtils.copyProperties(carAndDriverDto, car);
        carMapper.insert(car);

        if (carAndDriverDto.getDriverId() != null && carAndDriverDto.getDriverId() != ""){
            BeanUtils.copyProperties(carAndDriverDto, driver);
            driverMapper.insert(driver);
        }
        return true;
    }

    @Override
    public boolean update(Car car) {
        carMapper.update(car);
        return true;
    }

    public boolean removeById(Integer id) {
        carMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public Car load(Car car) {
        return carMapper.selectByExample(car);
    }

    @Override
    public Car getById(Integer id) {
        return carMapper.selectByPrimaryKey(id);
    }

    public Page<CarAndDriverVo> getCarListByPage(Long current, Long size, Car car) {

        Page<Car> page = new Page(current, size);
        Page<CarAndDriverVo> pageRes = carMapper.selectByPage(page, car);
        return pageRes;
    }

    @Override
    public Integer countRegistered() {
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.select("Distinct car_id");
        wrapper.eq("is_delete", 0);
        return carMapper.selectCount(wrapper);
    }
}
