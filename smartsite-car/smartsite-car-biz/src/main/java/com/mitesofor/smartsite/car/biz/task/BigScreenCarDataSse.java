//package com.mitesofor.smartsite.car.biz.task;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.mitesofor.smartsite.car.api.vo.BigScreenCarDataSseVo;
//import com.mitesofor.smartsite.car.biz.common.NonRequestAttributes;
//import com.mitesofor.smartsite.message.api.feign.SendSseMessageService;
//import com.xxl.job.core.handler.annotation.XxlJob;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//
//import java.time.LocalDateTime;
//import java.util.Random;
//
///**
// * @Description 模拟大屏车辆实时数据
// * @Author jchen
// * @Date 2023/7/28 8:53
// */
//@Component
//public class BigScreenCarDataSse {
//
//
//    private static Logger logger = LoggerFactory.getLogger(BigScreenCarDataSse.class);
//
//    @Autowired
//    SendSseMessageService sendSseMessageService;
//
//    Random random = new Random();
//
//    int noRegisterMin = 0;
//
//    int noRegisterMax = 8;
//
//    int registerMin = 10;
//
//    int registerMax = 20;
//
//    int inMin = 30;
//
//    int inMax = 50;
//
//    int outMin = 30;
//
//    int outMax = 50;
//
//    /**
//     * 车辆进出实时数据模拟
//     */
//    @XxlJob("carDataTaskMethod")
//    public void carDataTaskMethod() throws Exception {
//        //IntelligenceUser user = SecurityUtils.getUser();
//        BigScreenCarDataSseVo bigScreenCarDataSseVo = new BigScreenCarDataSseVo(random.nextInt(registerMax - registerMin + 1) + registerMin,random.nextInt(noRegisterMax - noRegisterMin + 1) + noRegisterMin,random.nextInt(inMax - inMin + 1) + inMin,random.nextInt(outMax - outMin + 1) + outMin);
////        if(null!=user){
////            sendSseMessageService.sendMessage(user.getId().toString(), JSONObject.toJSONString(bigScreenCarDataSseVo));
////        }
//        try {
//            //处理定时任务和异步程序中调用Feign的问题(定时任务是非web请求，无法获取请求上下文的requestAttributes，不携带Request参数)
//            RequestAttributes requestAttributes;
//            try {
//                requestAttributes = RequestContextHolder.currentRequestAttributes();
//            } catch (IllegalStateException e) {
//                requestAttributes = new NonRequestAttributes();
//            }
//
//            RequestContextHolder.setRequestAttributes(requestAttributes, true);
//            logger.info("发送车辆实时数据，当前时间:{}",LocalDateTime.now());
//            sendSseMessageService.push(JSON.toJSONString(bigScreenCarDataSseVo));
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
////    public static void main(String[] args) {
////        Random random = new Random();
////
////        int noRegisterMin = 0;
////
////        int noRegisterMax = 8;
////
////        int registerMin = 10;
////
////        int registerMax = 20;
////
////        int inMin = 30;
////
////        int inMax = 50;
////
////        int outMin = 30;
////
////        int outMax = 50;
////
////        BigScreenCarDataSseVo bigScreenCarDataSseVo = new BigScreenCarDataSseVo(random.nextInt(registerMax - registerMin + 1) + registerMin,random.nextInt(noRegisterMax - noRegisterMin + 1) + noRegisterMin,random.nextInt(inMax - inMin + 1) + inMin,random.nextInt(outMax - outMin + 1) + outMin);
////        System.out.println(JSON.toJSONString(bigScreenCarDataSseVo));
////    }
//
//}
