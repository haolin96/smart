package com.mitesofor.smartsite.person.biz.controller;

import cn.hutool.core.lang.Assert;
import com.mitesofor.smartsite.common.core.util.R;
import com.mitesofor.smartsite.person.api.entity.Job;
import com.mitesofor.smartsite.person.api.entity.Person;
import com.mitesofor.smartsite.person.biz.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@Api(value = "岗位信息", tags = "岗位信息", description = "统计岗位信息")
public class JobController {

    @Autowired
    public JobService jobService;

    @ApiOperation(value = "新增岗位", notes = "新增岗位")
    @PostMapping("/save")
    public R save(@RequestBody Job job){
        try{
            boolean res = jobService.save(job);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }
    @ApiOperation(value = "修改岗位", notes = "修改岗位")
    @PostMapping("/update")
    public R update(@RequestBody Job job){
        Assert.notBlank(job.getJobName(), "请输入岗位名称");
        try{
            boolean res = jobService.updateById(job);
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "删除岗位", notes = "删除岗位")
    @PostMapping("/delete")
    public R delete(@RequestBody Job job){
        Assert.notBlank(job.getJobName(), "请输入岗位名称");
        try{
            boolean res = jobService.removeById(job.getJobName());
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }

    @ApiOperation(value = "查询岗位", notes = "查询岗位")
    @GetMapping("/load")
    public R load(@RequestBody Job job){
        Assert.notBlank(job.getJobName(), "请输入岗位名称");
        try{
            Job res = jobService.getById(job.getJobName());
            return R.ok(res, "success");
        }catch(Exception e){
            return R.failed(e, "failed");
        }
    }
}
