package cn.antigenmhc.otaku.service.trade.service.impl;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.service.base.dto.AnimeDto;
import cn.antigenmhc.otaku.service.base.dto.MemberDto;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.trade.pojo.Order;
import cn.antigenmhc.otaku.service.trade.mapper.OrderMapper;
import cn.antigenmhc.otaku.service.trade.remote.RemoteAdminService;
import cn.antigenmhc.otaku.service.trade.remote.RemoteUcenterService;
import cn.antigenmhc.otaku.service.trade.service.OrderService;
import cn.antigenmhc.otaku.service.trade.utils.OrderNumUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private RemoteAdminService remoteAdminService;
    @Resource
    private RemoteUcenterService remoteUcenterService;

    @Override
    public Order getOrderById(String orderId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("id", orderId);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public String addOrder(String animeId, String memberId) {

        //查询该用户是否对该动漫下了订单
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("anime_id", animeId);
        queryWrapper.eq("member_id", memberId);
        Order order = baseMapper.selectOne(queryWrapper);

        //如果订单已经存在，直接返回 orderId
        if(order != null){
            return order.getId();
        }

        //如果订单不存在，则创建订单
        AnimeDto animeDto = remoteAdminService.getAnimeDtoByAnimeId(animeId);
        MemberDto memberDto = remoteUcenterService.getMemberDtoByMemberId(memberId);
        if(memberDto == null || animeDto == null){
            throw new IntegrateException(ResultCodeEnum.FEIGN_REMOTE_FAIL);
        }

        //创建订单
        Order newOrder = new Order();
        //订单号
        newOrder.setOrderNo(OrderNumUtils.getOrderNo());

        newOrder
                //动漫部分信息
                .setAnimeId(animeId)
                .setAdminName(animeDto.getAdminName())
                .setAnimeCover(animeDto.getCover())
                .setAnimeTitle(animeDto.getTitle())
                .setTotalFee(animeDto.getPrice().multiply(new BigDecimal("100")))
                //用户部分信息
                .setMemberId(memberId)
                .setMobile(memberDto.getMobile())
                .setNickname(memberDto.getNickname())
                //订单状态（0：未支付，1：已支付）
                .setStatus(0)
                //支付类型（1：微信，2：支付宝）
                .setPayType(1);

        baseMapper.insert(newOrder);
        return newOrder.getId();
    }

    @Override
    public boolean getOrderBoughtStateById(String animeId, String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("member_id", memberId);
        queryWrapper.eq("anime_id", animeId);
        queryWrapper.eq("status",1);
        return baseMapper.selectOne(queryWrapper) != null;
    }

    @Override
    public List<Order> getOrderList(String memberId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .orderByDesc("gmt_create")
                .eq("member_id", memberId);
        List<Order> orders = baseMapper.selectList(queryWrapper);
        return orders;
    }

    @Override
    public boolean deleteOrderById(String orderId, String memberId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("id", orderId)
                .eq("member_id", memberId);
        return this.remove(queryWrapper);
    }
}
