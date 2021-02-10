package cn.antigenmhc.otaku.service.statistics.pojo;

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
 * 网站统计日数据
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("statistics_daily")
@ApiModel(value="Daily对象", description="网站统计日数据")
public class Daily extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "统计日期")
    private String dateCalculated;

    @ApiModelProperty(value = "注册人数")
    private Integer registerNum;

    @ApiModelProperty(value = "登录人数")
    private Integer loginNum;

    @ApiModelProperty(value = "每日播放视频数")
    private Integer videoViewNum;

    @ApiModelProperty(value = "每日新增课程数")
    private Integer animeNum;

    @ApiModelProperty(value = "gitee的用户数")
    private Integer giteeNum;

    @ApiModelProperty(value = "github的用户数")
    private Integer githubNum;
}
