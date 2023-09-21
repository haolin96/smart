package com.mitesofor.smartsite.warehouse.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Description
 * @Author jchen
 * @Date 2023/9/13 17:22
 */
@Data
public class MaterialLogQueryDto {
    @ApiModelProperty(value="物料编号")
    private String materialSn;

    /**
     * model
     */
    @ApiModelProperty(value="类型")
    private String type;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;
}
