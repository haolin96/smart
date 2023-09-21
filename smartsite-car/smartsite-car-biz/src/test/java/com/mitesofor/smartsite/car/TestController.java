package com.mitesofor.smartsite.car;

import com.alibaba.fastjson.JSONObject;
import com.mitesofor.smartsite.car.api.entity.Car;
import com.mitesofor.smartsite.car.api.entity.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author jchen
 * @Date 2023/8/2 9:05
 */
@SpringBootTest(classes = TestController.class)
public class TestController {

    @Test
    public void test() {
//        String r = "{\"driverId\": \"36256542001\",\"driverName\": \"李四\",\"driverLicensePhoto\": \"\",\"driverCompany\": \"中铁四局\",\"phone\": \"19858455485\",\"address\": \"江西南昌\"}";
//        Driver2 object = JSONObject.toJavaObject(JSONObject.parseObject(r),Driver2.class);
//        instanceofObj(object);
//        Driver2 object = new Driver2();
//        object.setAddress("123");
//        System.out.println(JSONObject.toJSONString(object));

//        int a = 5;
//
//        if(1<5){
//            System.out.println("1:"+true);
//        }else if(2<5){
//            System.out.println("2:"+true);
//        }

        List<String> list = new ArrayList<>();
        list.add("1");
        List<String> list2 = new ArrayList<>();
        list2.add("2");
        List<String> list3 = new ArrayList<>();
        list3.add("3");

        List<String> list4 = new ArrayList<>();
        list4.addAll(list);
        list4.addAll(list2);
        list4.addAll(list3);

        list4.forEach(s -> System.out.println("value="+s));
    }

    public void instanceofObj(Object o) {

        if(o instanceof Driver){
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }
}
