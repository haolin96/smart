package com.mitesofor.smartsite.car.biz.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.car.api.entity.Driver;
import com.mitesofor.smartsite.car.biz.mapper.DriverMapper;
import com.mitesofor.smartsite.car.biz.service.DriverService;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl extends ServiceImpl<DriverMapper, Driver> implements DriverService {
}
