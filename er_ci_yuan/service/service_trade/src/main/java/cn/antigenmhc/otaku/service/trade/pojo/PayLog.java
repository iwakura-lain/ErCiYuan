package cn.antigenmhc.otaku.service.trade.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("trade_pay_log")
@ApiModel(value="PayLog对象", description="支付日志表")
public class PayLog extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "支付完成时间")
    private Date payTime;

    @ApiModelProperty(value = "支付金额（分）")
    private Long totalFee;

    @ApiModelProperty(value = "交易流水号")
    private String transactionId;

    @ApiModelProperty(value = "交易状态")
    private String tradeState;

    @ApiModelProperty(value = "支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty(value = "其他属性")
    private String attr;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
