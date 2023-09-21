package com.mitesofor.smartsite.person.api.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class PersonJobStat {

    // 保存对应工作种类的人数
    public HashMap<String, Integer> job;
}
