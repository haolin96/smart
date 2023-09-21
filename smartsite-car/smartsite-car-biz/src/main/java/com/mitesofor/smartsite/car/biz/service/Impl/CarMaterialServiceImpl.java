package com.mitesofor.smartsite.car.biz.service.Impl;

import cn.hutool.core.lang.Assert;
import com.mitesofor.smartsite.car.api.dto.CarInAndOutDto;
import com.mitesofor.smartsite.car.api.entity.CarMaterial;
import com.mitesofor.smartsite.car.api.vo.CarMaterialVo;
import com.mitesofor.smartsite.car.biz.mapper.CarMaterialMapper;
import com.mitesofor.smartsite.car.biz.service.CarInAndOutService;
import com.mitesofor.smartsite.car.biz.service.CarMaterialServcie;
import com.mitesofor.smartsite.common.core.constant.CommonConstants;
import com.mitesofor.smartsite.common.security.service.IntelligenceUser;
import com.mitesofor.smartsite.common.security.util.SecurityUtils;
import com.mitesofor.smartsite.warehouse.api.entity.MaterialInForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class CarMaterialServiceImpl implements CarMaterialServcie {

    @Autowired
    public CarMaterialMapper carMaterialMapper;

    @Autowired
    public CarInAndOutService carInAndOutService;

    @Override
    public List<CarMaterial> getCarMaterial() {
        return carMaterialMapper.selectLatestList();
    }

    public int save(CarMaterial carMaterial) {
        return carMaterialMapper.insert(carMaterial);
    }
    @Override
    public void produceData() {
        String[] drivers = new String[] {"熊小宝", "杨先荣", "赵志刚"};
        String[] carIds = new String[] {"赣AF2456", "赣AA7888", "赣MM1236"};
        Double[] carWeights = new Double[] {10.7, 8.8, 17.5};
        Random random = new Random();
        // 随机选一辆车
        int num = random.nextInt(3);
        String driver = drivers[num];
        String carId = carIds[num];
        Double carWeight = carWeights[num];

        // 查询是否有进场
        CarMaterial carMaterialOrigin = carMaterialMapper.selectLatestOne(carId);

        CarMaterial carMaterial = new CarMaterial();
        // 如果这辆车之前有进场，则让它出场
        if (carMaterialOrigin != null && carMaterialOrigin.getInOrOut() == 1){
            BeanUtils.copyProperties(carMaterialOrigin, carMaterial, "id");
            carMaterial.setInOrOut(0);

            carMaterial.setCreateTime(LocalDateTime.now());

            Double weight = carMaterialOrigin.getWeight() - carMaterialOrigin.getMaterialWeight();
            carMaterial.setWeight(weight);
        }
        // 如果之前没有进场，则生成以下信息
        else {
            // 随机生成物料种类
            int materialId = random.nextInt(16) + 17;
            CarMaterialVo carMaterialVo = carMaterialMapper.selectMaterialInfo(materialId);

            // 随机生成物料重量
            Double materialWeight = random.nextDouble() * 30 + 1;

            Double weight = materialWeight + carWeight;

            BeanUtils.copyProperties(carMaterialVo, carMaterial);
            carMaterial.setCarId(carId);
            carMaterial.setDriver(driver);
            carMaterial.setWeight(weight);
            carMaterial.setMaterialWeight(materialWeight);
            carMaterial.setInOrOut(1);
            carMaterial.setCreateTime(LocalDateTime.now());
        }
        // 将车辆物料数据保存
        this.save(carMaterial);

        // 生成车辆进场信息并发送
        CarInAndOutDto carInAndOutDto = new CarInAndOutDto();
        BeanUtils.copyProperties(carMaterial, carInAndOutDto);
        carInAndOutService.save(carInAndOutDto);

        //生成物料入库信息并发送
        MaterialInForm materialInForm = new MaterialInForm();

        materialInForm.setMaterialId(carMaterial.getMaterialId());

        Double materialWeight = carMaterial.getMaterialWeight();

        if (carMaterial.getInOrOut() != 0){
            materialInForm.setFormNumber(BigDecimal.valueOf(materialWeight));

            Double lossRatio = random.nextDouble() * 0.05;

            materialInForm.setRealNumber(BigDecimal.valueOf(materialWeight * lossRatio));

            materialInForm.setReviewState("1");
        }
        else {
            materialInForm.setReviewState("0");
        }

        materialInForm.setCreateBy(1);
        materialInForm.setApplicant("admin");
        materialInForm.setApplicantId(1);
        materialInForm.setReviewerId(1);
        materialInForm.setReviewer("admin");
        this.saveMaterial(materialInForm);

    }

    private void saveMaterial(MaterialInForm materialInForm){
//        materialInForm.setReviewState(CommonConstants.AUDIT);
        materialInForm.setApplyTime(LocalDateTime.now());
        materialInForm.setCreateTime(LocalDateTime.now());
        materialInForm.setUpdateTime(LocalDateTime.now());

        carMaterialMapper.insertMaterial(materialInForm);
    }

}
