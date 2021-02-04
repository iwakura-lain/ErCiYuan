package cn.antigenmhc.otaku.service.trade.mapper;

import cn.antigenmhc.otaku.service.trade.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> getOrderList(String memberId);
}
