package cn.antigenmhc.otaku.service.manager.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: antigenMHC
 * @Date: 2020/12/6 22:47
 * @Version: 1.0
 **/
@ApiModel("Admin查询条件封装")
@Data
public class AdminQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("管理员名称")
    private String name;

    @ApiModelProperty("管理员等级")
    private Integer level;

    @ApiModelProperty("查询起始时间")
    private String joinDateBegin;

    @ApiModelProperty("查询结束时间")
    private String joinDateEnd;
}
