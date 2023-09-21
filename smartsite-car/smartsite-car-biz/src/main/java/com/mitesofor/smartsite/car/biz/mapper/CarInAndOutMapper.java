package com.mitesofor.smartsite.car.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.CarInAndOut;
import com.mitesofor.smartsite.car.api.vo.CarInAndOutVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarInAndOutMapper extends BaseMapper<CarInAndOut> {

    void update(CarInAndOut carInAndOut);
    void deleteByPrimaryKey(Integer id);

    CarInAndOut selectByPrimaryKey(Integer id);

    Page<CarInAndOutVo> selectByPage(Page<CarInAndOut> page,
                                     @Param("carInAndOut") CarInAndOut carInAndOut,
                                     @Param("car") Car car,
                                     @Param("time") String time);

    List<CarInAndOut> selectByExample(@Param("carInAndOut") CarInAndOut carInAndOut);

    List<CarInAndOut> selectBySevenDays();

    CarInAndOutVo selectLatestIn();

    CarInAndOutVo selectLatestOut();
}
