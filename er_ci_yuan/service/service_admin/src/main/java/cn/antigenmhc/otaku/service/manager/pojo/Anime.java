package cn.antigenmhc.otaku.service.manager.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("otaku_anime")
@ApiModel(value="Anime对象", description="动漫")
public class Anime extends BasePojo {

    private static final long serialVersionUID=1L;

    public static final String ANIME_DRAFT = "Draft";
    public static final String ANIME_NORMAL = "Normal";

    @ApiModelProperty(value = "动漫管理员ID")
    private String adminId;

    @ApiModelProperty(value = "动漫分类ID")
    private String subjectId;

    @ApiModelProperty(value = "动漫分类父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "动漫名称")
    private String title;

    @ApiModelProperty(value = "动漫销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总集数")
    private Integer animeNum;

    @ApiModelProperty(value = "动漫封面图片路径")
    private String cover;

    @ApiModelProperty(value = "销售数量")
    private Long buyCount;

    @ApiModelProperty(value = "浏览数量")
    private Long viewCount;

    @ApiModelProperty(value = "乐观锁")
    @Version
    private Long version;

    @ApiModelProperty(value = "动漫状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;
}
