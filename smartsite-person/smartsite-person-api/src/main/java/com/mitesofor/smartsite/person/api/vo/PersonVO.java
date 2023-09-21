package com.mitesofor.smartsite.person.api.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mitesofor.smartsite.person.api.entity.Person;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PersonVO extends Person {

    @ApiModelProperty(value = "项目名称")
    private String projectName;
}
