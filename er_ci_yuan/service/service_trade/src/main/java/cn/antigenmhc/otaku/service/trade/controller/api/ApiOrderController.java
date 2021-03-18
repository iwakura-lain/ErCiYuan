package cn.antigenmhc.otaku.service.trade.controller.api;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.common.base.utils.JwtUtil;
import cn.antigenmhc.otaku.common.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.trade.pojo.Order;
import cn.antigenmhc.otaku.service.trade.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-02
 */
@Api(description = "前台订单管理")
@RestController
@RequestMapping("/api/trade/order")
@Slf4j
public class ApiOrderController {

    @Resource
    private OrderService orderService;
    @Resource
    private RedisUtil redisUtil;

    /**
     *  auth 路径代表用户需要先登录
     */
    @ApiOperation("新增订单")
    @PostMapping("auth/add/{animeId}")
    public Result addOrder(@PathVariable("animeId") String animeId,
                           HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        String orderId = orderService.addOrder(animeId, jwtInfo.getId());
        return Result.ok().setData("orderId", orderId).setData("token", newToken);
    }

    @ApiOperation("获得订单信息")
    @GetMapping("auth/get/{orderId}")
    public Result getOrder(@PathVariable("orderId") String orderId,
                           HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        Order order = orderService.getOrderById(orderId, jwtInfo.getId());
        return Result.ok().setData("item", order).setData("token", newToken);
    }

    @ApiOperation("查询当前用户对动漫的购买状态")
    @GetMapping("auth/is-buy/{animeId}")
    public Result isBuyAnimeId(@PathVariable("animeId") String animeId,
                               HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        boolean isBought = orderService.getOrderBoughtStateById(animeId, jwtInfo.getId());
        return Result.ok().setData("isBuy", isBought).setData("token", newToken);
    }

    @ApiOperation("查询当前用户的订单列表")
    @GetMapping("auth/list")
    public Result getOrderList(HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        List<Order> orders = orderService.getOrderList(jwtInfo.getId());
        return Result.ok().setData("items", orders).setData("token", newToken);
    }

    @ApiOperation("根据 id 删除订单")
    @DeleteMapping("auth/delete/{orderId}")
    public Result delete(@PathVariable("orderId") String orderId,
                         HttpServletRequest request){
        String token = request.getHeader("token");
        String newToken = JwtUtil.checkTokenExpireTimeAndGetNew(token, redisUtil);
        JwtInfo jwtInfo = JwtUtil.getMemberByJwtToken(newToken);

        boolean isDeleted = orderService.deleteOrderById(orderId, jwtInfo.getId());
        if(isDeleted){
            return Result.ok().setMessage("删除成功");
        }else{
            return Result.error().setMessage("删除失败");
        }
    }

    @ApiOperation("查询支付结果")
    @GetMapping("query-pay-status/{orderNo}")
    public Result queryPayStatus(@PathVariable("orderNo") String orderNo){
        Order order = orderService.queryOrderStatus(orderNo);
        if(order == null){
            return Result.setResult(ResultCodeEnum.PAY_ORDERQUERY_ERROR);
        }
        if(order.getStatus() == 1){
            return Result.ok().setMessage("支付成功");
        }
        return Result.setResult(ResultCodeEnum.PAY_RUN);
    }
}

