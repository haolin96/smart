package com.mitesofor.smartsite.car.api.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Description
 * @Author jchen
 * @Date 2023/7/28 9:02
 */
@Data
public class BigScreenCarDataSseVo  extends Model<BigScreenCarDataSseVo> {
    private static final long serialVersionUID = 1L;

    private Integer registerCarNum;

    private Integer noRegisterCarNum;

    private Integer inCarNum;

    private Integer outCarNum;

    public BigScreenCarDataSseVo() {
    }

    public BigScreenCarDataSseVo(Integer registerCarNum, Integer noRegisterCarNum, Integer inCarNum, Integer outCarNum) {
        this.registerCarNum = registerCarNum;
        this.noRegisterCarNum = noRegisterCarNum;
        this.inCarNum = inCarNum;
        this.outCarNum = outCarNum;
    }
}
