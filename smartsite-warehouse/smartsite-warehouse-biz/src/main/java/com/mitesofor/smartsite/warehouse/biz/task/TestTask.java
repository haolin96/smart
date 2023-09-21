package com.mitesofor.smartsite.warehouse.biz.task;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/26 17:26
 */
@Component
public class TestTask {

    private static Logger logger = LoggerFactory.getLogger(TestTask.class);


    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("testTaskMethod")
    public void testTaskMethod() throws Exception {
        XxlJobHelper.log("XXL-JOB, Hello World.");
        System.out.println("测试任务");
    }
}
