package com.mitesofor.smartsite.person.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Job extends Model<Job> {
    /**
     * id
     */
    @ApiModelProperty(value = "主键", required = true)
    private String id;
    /**
     * 岗位名
     */
    @ApiModelProperty(value = "岗位名称", required = true)
    private String jobName;
    /**
     * 岗位类型
     */
    @ApiModelProperty(value = "岗位类型", required = true)
    private String jobType;

}
