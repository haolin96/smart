package com.mitesofor.smartsite.person.api.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class PersonAgeStat {

    // 保存年龄统计数据，对应年龄段的人数
    private HashMap<String, Integer> map;
}
