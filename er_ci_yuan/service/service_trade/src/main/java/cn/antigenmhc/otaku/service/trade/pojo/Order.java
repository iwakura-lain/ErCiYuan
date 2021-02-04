package cn.antigenmhc.otaku.service.trade.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("trade_order")
@ApiModel(value="Order对象", description="订单")
public class Order extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "动漫id")
    private String animeId;

    @ApiModelProperty(value = "动漫名称")
    private String animeTitle;

    @ApiModelProperty(value = "动漫封面")
    private String animeCover;

    @ApiModelProperty(value = "讲师名称")
    private String adminName;

    @ApiModelProperty(value = "会员id")
    private String memberId;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员手机")
    private String mobile;

    @ApiModelProperty(value = "订单金额（分）")
    private BigDecimal totalFee;

    @ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty(value = "订单状态（0：未支付 1：已支付）")
    private Integer status;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
