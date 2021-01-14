package cn.antigenmhc.otaku.service.manager.pojo.form;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/12 22:52
 * @Version: 1.0
 **/
@ApiModel("动漫基本信息")
@Data
public class AnimeInfoForm implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("动漫编号")
    private String id;

    @ApiModelProperty("制作方ID")
    private String adminId;

    @ApiModelProperty("类目ID")
    private String subjectId;

    @ApiModelProperty("父级类目ID")
    private String subjectParentId;

    @ApiModelProperty("动漫名称")
    private String title;

    @ApiModelProperty("价格")
    private BigDecimal price;

    @ApiModelProperty("总集数")
    private Integer animeNum;

    @ApiModelProperty("动漫封面")
    private String cover;

    @ApiModelProperty("动漫简介")
    private String description;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;
}
