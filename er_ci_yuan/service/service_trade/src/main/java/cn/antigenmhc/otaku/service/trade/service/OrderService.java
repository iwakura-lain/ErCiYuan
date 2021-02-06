package cn.antigenmhc.otaku.service.trade.service;

import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.service.trade.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
public interface OrderService extends IService<Order> {


    Order getOrderById(String orderId, String memberId);


    String addOrder(String animeId, String memberId);

    boolean getOrderBoughtStateById(String animeId, String memberId);

    List<Order> getOrderList(String memberId);

    boolean deleteOrderById(String orderId, String memberId);

    Order getOrderByOrderNo(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Order queryOrderStatus(String orderNo);
}
