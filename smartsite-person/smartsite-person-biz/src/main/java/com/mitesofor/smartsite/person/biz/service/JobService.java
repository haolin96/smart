package com.mitesofor.smartsite.person.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mitesofor.smartsite.person.api.entity.Job;

import java.util.List;

public interface JobService extends IService<Job> {
    List<String> loadAllJob();

    List<String> loadAllJobType();
}
