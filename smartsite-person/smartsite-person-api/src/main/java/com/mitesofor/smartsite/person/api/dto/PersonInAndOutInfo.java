package com.mitesofor.smartsite.person.api.dto;

import lombok.Data;

import java.sql.Time;

@Data
public class PersonInAndOutInfo {
    private String personName;
    private String job;
    private Integer inOrOut;
    private Time time;
}
