package com.mitesofor.smartsite.car.biz.service.Impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.car.api.dto.CarInAndOutDto;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.CarInAndOut;
import com.mitesofor.smartsite.car.api.vo.CarInAndOutVo;
import com.mitesofor.smartsite.car.biz.mapper.CarInAndOutMapper;
import com.mitesofor.smartsite.car.biz.mapper.CarMapper;
import com.mitesofor.smartsite.car.biz.service.CarInAndOutService;
import com.mitesofor.smartsite.car.biz.service.CarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarInAndOutServiceImpl extends ServiceImpl<CarInAndOutMapper, CarInAndOut> implements CarInAndOutService {

    @Autowired
    public CarInAndOutMapper carInAndOutMapper;

    @Autowired
    public CarMapper carMapper;

    @Autowired
    public CarService carService;
    @Override
    public boolean save(CarInAndOutDto carInAndOutDto) {
        if (carInAndOutDto.getInOrOutTime() == null) {
            Date date = new Date();
            carInAndOutDto.setInOrOutTime(date);
        }
        QueryWrapper<Car> wrapper = new QueryWrapper<>();
        wrapper.eq("car_id", carInAndOutDto.getCarId());
        Car car = carMapper.selectOne(wrapper);
        if (car != null) {
            carInAndOutDto.setIsRegistered(1);
            carInAndOutDto.setCarBodyInoutPhoto(car.getCarBodyPhoto());
            carInAndOutDto.setCarHeadInoutPhoto(car.getCarHeadPhoto());
            carInAndOutDto.setCarTailInoutPhoto(car.getCarTailPhoto());
            carInAndOutDto.setLicensePlateInoutPhoto(car.getLicensePlatePhoto());
        }
        else{
            carInAndOutDto.setIsRegistered(0);
        }
        carInAndOutDto.setIsDelete(0);
        CarInAndOut carInAndOut = new CarInAndOut();

        BeanUtils.copyProperties(carInAndOutDto, carInAndOut);

        carInAndOutMapper.insert(carInAndOut);
        return true;
    }

    @Override
    public boolean update(CarInAndOut carInAndOut) {
        carInAndOutMapper.update(carInAndOut);
        return true;
    }

    @Override
    public boolean removeById(Integer id) {
        carInAndOutMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public CarInAndOut getById(Integer id) {
        return carInAndOutMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CarInAndOutVo> getListByPage(Long current, Long size, CarInAndOutDto carInAndOutDto) {
        Car car = new Car();
        CarInAndOut carInAndOut = new CarInAndOut();
        BeanUtils.copyProperties(carInAndOutDto, car);
        BeanUtils.copyProperties(carInAndOutDto, carInAndOut);
        Page<CarInAndOut> page = new Page(current, size);
        Page<CarInAndOutVo> pageRes = carInAndOutMapper.selectByPage(page, carInAndOut, car, "");
        return pageRes;
    }

    @Override
    public Page<CarInAndOutVo> getListByPageToday(Long current, Long size, CarInAndOutDto carInAndOutDto) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);
        Car car = new Car();
        CarInAndOut carInAndOut = new CarInAndOut();
        BeanUtils.copyProperties(carInAndOutDto, car);
        BeanUtils.copyProperties(carInAndOutDto, carInAndOut);
        Page<CarInAndOut> page = new Page(current, size);
        Page<CarInAndOutVo> pageRes = carInAndOutMapper.selectByPage(page, carInAndOut, car, today);
        return pageRes;
    }

//    @Override
//    public Page<CarInAndOutVO> getListByPageToday(Long current, Long size, CarInAndOut carInAndOut) {
//        return null;
//    }
    private List<CarInAndOut> getListToday() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(date);

        QueryWrapper<CarInAndOut> wrapper = new QueryWrapper<>();
        wrapper.like("in_or_out_time", today);

        List<CarInAndOut> carInAndOutList = carInAndOutMapper.selectList(wrapper);

        return carInAndOutList;
    }
    @Override
    public Map<String, Long> countInAndOutToday() {

        List<CarInAndOut> carInAndOutList = this.getListToday();

        Long carIn = carInAndOutList.stream().filter(a -> a.getInOrOut().equals(1)).count();
        Long carOut = carInAndOutList.stream().filter(a -> a.getInOrOut().equals(0)).count();
        Long carOnsite = carIn - carOut;
        if (carOnsite < 0){
            carOnsite = 0L;
        }
        Long countUnRegisteredInToday = carInAndOutList.stream()
                .filter(a -> (a.getIsRegistered() == 0 && a.getInOrOut() == 1))
                .count();
        Long countRegistered = carService.countRegistered().longValue();
        Map<String, Long> resMap = new HashMap<>();
        resMap.put("registeredCar", countRegistered);
        resMap.put("in", carIn);
        resMap.put("out", carOut);
        resMap.put("onsite", carOnsite);
        resMap.put("UnregisteredIn", countUnRegisteredInToday);
        return resMap;
    }

    public Map<Integer, Map<String, Long>> CountInAndOutByHour(){

        List<CarInAndOut> carInAndOutListToday = this.getListToday();

        Map<Integer, List<CarInAndOut>> carInAndOutMapList = carInAndOutListToday.stream()
                .collect(Collectors.groupingBy(a -> a.getInOrOutTime().getHours()));

        Map<Integer, Map<String, Long>> carInAndOutMapByHour = new HashMap<>();
        Long countOnsite = 0L;
        for (int hour = 0; hour < 24; hour++){
            List<CarInAndOut> carList = carInAndOutMapList.getOrDefault(hour, new ArrayList<>());
            Long countIn = carList.stream()
                    .filter(a -> a.getInOrOut().equals(1))
                    .count();
            Long countOut = carList.stream()
                    .filter(a -> a.getInOrOut().equals(0))
                    .count();

            countOnsite += countIn - countOut;
            if (countOnsite < 0){
                countOnsite = 0L;
            }

            Map<String, Long> carInAndOutMap = new HashMap<>();
            carInAndOutMap.put("in", countIn);
            carInAndOutMap.put("out", countOut);
            carInAndOutMap.put("onsite", countOnsite);

            carInAndOutMapByHour.put(hour, carInAndOutMap);
        }

        return carInAndOutMapByHour;

    }

    public Map<String, Map<String, Long>> countInAndOutBySevenDays() {
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)-7);
//        Date weekBefore = calendar.getTime();
//
//        QueryWrapper<CarInAndOut> wrapper = new QueryWrapper<>();
//        wrapper.gt("in_or_out_time", "date_sub(Now(), ")
        List<CarInAndOut> carInAndOutList = carInAndOutMapper.selectBySevenDays();

        Map<String, List<CarInAndOut>> carInAndOutMapList = carInAndOutList.stream()
                .collect(Collectors.groupingBy(a -> DateUtil.format(a.getInOrOutTime(), "yyyy-MM-dd")));

//        Map<String, Map<String, Long>> carInAndOutCountMap = new HashMap<>();

        Map<String, Map<String, Long>> carInAndOutCountMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for (String key: carInAndOutMapList.keySet()) {
            List<CarInAndOut> carInAndOutListTmp = carInAndOutMapList.get(key);
            Long countRegistered = carInAndOutListTmp.stream()
                    .filter(a -> a.getIsRegistered() == 1)
                    .count();

            Long countUnregistered = carInAndOutListTmp.stream()
                    .filter(a -> a.getIsRegistered() == 0)
                    .count();

            Map<String, Long> carInAndOutMap = new HashMap<>();
            carInAndOutMap.put("Registered", countRegistered);
            carInAndOutMap.put("Unregistered", countUnregistered);

            carInAndOutCountMap.put(key, carInAndOutMap);
        }

        return carInAndOutCountMap;
    }

//    @Override
//    public boolean checkRegistered(String carId) {
//        QueryWrapper<Car> wrapper = new QueryWrapper<>();
//        wrapper.eq("car_id", carId);
//        if (carMapper.selectOne(wrapper) == null) {
//            return false;
//        }
//        else {
//            return true;
//        }
//    }

    @Override
    public CarInAndOutVo getLatestIn() {
        CarInAndOutVo carInAndOutVO = carInAndOutMapper.selectLatestIn();
        if (carInAndOutVO.getCarBodyInoutPhoto() == null){
            carInAndOutVO.setCarBodyInoutPhoto("2eec6e7d415748ac837c68b760f4aa24.png");
        }
        if (carInAndOutVO.getCarHeadInoutPhoto() == null){
            carInAndOutVO.setCarHeadInoutPhoto("b2f4d673c646465a88019e4383d23b28.png");
        }
        if (carInAndOutVO.getCarTailInoutPhoto() == null){
            carInAndOutVO.setCarTailInoutPhoto("0e49019ff6b74571bae8329e66a907e5.png");
        }
        if (carInAndOutVO.getLicensePlateInoutPhoto() == null){
            carInAndOutVO.setLicensePlateInoutPhoto("773cd0e0231942939734de53e26ad94c.png");
        }
        return carInAndOutVO;
    }

    @Override
    public CarInAndOutVo getLatestOut() {
        CarInAndOutVo carInAndOutVO = carInAndOutMapper.selectLatestOut();
        if (carInAndOutVO.getCarBodyInoutPhoto() == null){
            carInAndOutVO.setCarBodyInoutPhoto("2eec6e7d415748ac837c68b760f4aa24.png");
        }
        if (carInAndOutVO.getCarHeadInoutPhoto() == null){
            carInAndOutVO.setCarHeadInoutPhoto("b2f4d673c646465a88019e4383d23b28.png");
        }
        if (carInAndOutVO.getCarTailInoutPhoto() == null){
            carInAndOutVO.setCarTailInoutPhoto("0e49019ff6b74571bae8329e66a907e5.png");
        }
        if (carInAndOutVO.getLicensePlateInoutPhoto() == null){
            carInAndOutVO.setLicensePlateInoutPhoto("773cd0e0231942939734de53e26ad94c.png");
        }
        return carInAndOutVO;
    }

    @Override
    public Long countUnRegisteredInToday() {
        List<CarInAndOut> carInAndOutList = this.getListToday();

        Long countToday = carInAndOutList.stream()
                .filter(a -> (a.getIsRegistered() == 0 && a.getInOrOut() == 1))
                .count();

        return countToday;
    }


}
