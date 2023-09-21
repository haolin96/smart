package com.mitesofor.smartsite.person.biz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mitesofor.smartsite.person.api.entity.Job;
import com.mitesofor.smartsite.person.biz.mapper.JobMapper;
import com.mitesofor.smartsite.person.biz.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, Job> implements JobService {

    @Autowired
    public JobMapper jobMapper;

    public List<String> loadAllJob() {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("Distinct job");

        List<String> joblist = jobMapper.selectList(queryWrapper)
                .stream()
                .map(Job::getJobName)
                .collect(Collectors.toList());
        return joblist;
    }

    public List<String> loadAllJobType() {
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("Distinct job_type");

        List<String> jobtypelist = jobMapper.selectList(queryWrapper)
                .stream()
                .map(Job::getJobType)
                .collect(Collectors.toList());
        return jobtypelist;
    }
}

