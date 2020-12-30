package cn.antigenmhc.otaku.service.manager.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 动漫
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("otaku_chapter")
@ApiModel(value="Chapter对象", description="动漫")
public class Chapter extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "动漫ID")
    private String animeId;

    @ApiModelProperty(value = "集数标题")
    private String title;

    @ApiModelProperty(value = "显示排序")
    private Integer sort;


}
