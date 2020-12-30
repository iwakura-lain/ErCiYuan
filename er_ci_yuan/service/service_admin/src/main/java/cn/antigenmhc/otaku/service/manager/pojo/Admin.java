package cn.antigenmhc.otaku.service.manager.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 管理员
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("otaku_admin")
@ApiModel(value="Admin对象", description="管理员")
public class Admin extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "管理员名称")
    private String name;

    @ApiModelProperty(value = "管理员简介")
    private String intro;

    @ApiModelProperty(value = "管理员签名")
    private String career;

    @ApiModelProperty(value = "头衔 1顶级老法师 2首席大法师")
    private Integer level;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;

    @ApiModelProperty(value = "入驻时间", example = "2020-01-01")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date joinDate;
}
