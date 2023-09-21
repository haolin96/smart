package com.mitesofor.smartsite.person.api.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class PersonInAndOutStat {

    private HashMap<String, Integer> map;
}
