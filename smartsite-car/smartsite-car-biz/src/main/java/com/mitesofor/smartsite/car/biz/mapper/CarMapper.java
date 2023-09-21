package com.mitesofor.smartsite.car.biz.mapper;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.car.api.vo.CarAndDriverVo;
import com.mitesofor.smartsite.car.api.entity.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CarMapper extends BaseMapper<Car> {

    void update(Car car);
    Car selectByPrimaryKey(Integer id);
    Car selectByExample(Car car);

    @SqlParser(filter = true)
    Page<CarAndDriverVo> selectByPage(Page<Car> page, @Param("car") Car car);
    void deleteByPrimaryKey(@Param("id") Integer id);
}
