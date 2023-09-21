package com.mitesofor.smartsite.car.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mitesofor.smartsite.car.api.entity.CarMaterial;
import com.mitesofor.smartsite.car.api.vo.CarMaterialVo;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarMaterialMapper extends BaseMapper<CarMaterial> {

    CarMaterialVo selectMaterialInfo(@Param("id") Integer id);

    CarMaterial selectLatestOne(@Param("carId") String carId);

    void insertMaterial(MaterialInForm materialInForm);

    List<CarMaterial> selectLatestList ();
}
