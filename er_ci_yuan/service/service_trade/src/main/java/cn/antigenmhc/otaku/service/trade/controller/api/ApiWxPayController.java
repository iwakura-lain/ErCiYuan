package cn.antigenmhc.otaku.service.trade.controller.api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.trade.pojo.Order;
import cn.antigenmhc.otaku.service.trade.service.OrderService;
import cn.antigenmhc.otaku.service.trade.service.WeixinPayService;
import cn.antigenmhc.otaku.service.trade.utils.StreamUtil;
import com.github.wxpay.sdk.WXPayUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: antigenMHC
 * @Date: 2021/2/4 19:21
 * @Version: 1.0
 **/
@Api(description = "支付接口")
@RestController
@RequestMapping("/api/trade/wx-pay")
public class ApiWxPayController {

    @Resource
    private WeixinPayService weixinPayService;
    @Resource
    private OrderService orderService;

    @ApiOperation("获取QRUrl")
    @GetMapping("create-native/{orderNo}")
    public Result createNative(@PathVariable("orderNo") String orderNo,
                               HttpServletRequest request){
        String remoteAddr = request.getRemoteAddr();
        Map<String, Object> nativeQrCode = weixinPayService.createNativeQrCode(orderNo, remoteAddr);
        return Result.ok().setData(nativeQrCode);
    }

    /**
     * 微信回调接口，必须是 post 接收
     * 注意，回调接口返回的是失败结果（或者超时）的情况下 vx 服务器会隔一段时间
     * 回调一次，直到 24h4m 后不再有回调
     * @param request：微信发送的请求
     * @param response：给微信的响应
     * @return
     */
    @ApiOperation("支付回调")
    @PostMapping("callback/notify")
    public String payNotify(HttpServletRequest request,
                            HttpServletResponse response) throws Exception{
        //获得通知结果
        ServletInputStream is = request.getInputStream();
        String notifyXml = StreamUtil.inputStream2String(is, "utf-8");

        Map<String, String> resMap = new HashMap<>();

        //验签
        if(weixinPayService.checkSignature(notifyXml)){

            Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyXml);

            //支付是否成功
            if("SUCCESS".equals(notifyMap.get("result_code"))){

                //比对订单金额是否一致
                String totalFee = notifyMap.get("total_fee");
                String outTradeNo = notifyMap.get("out_trade_no");
                Order order = orderService.getOrderByOrderNo(outTradeNo);

                if(order != null && order.getTotalFee().intValue() == Integer.parseInt(totalFee)){
                    //判断订单状态，保持回调接口的幂等性
                    //如果订单状态已经为 1 了，则直接返回一样的信息；
                    // 否则更新订单后再返回
                    if(order.getStatus() == 0){
                        //更新订单状态
                        orderService.updateOrderStatus(notifyMap);
                    }
                    resMap.put("return_code", "SUCCESS");
                    resMap.put("return_msg","OK");
                    String responseXml = WXPayUtil.mapToXml(resMap);
                    response.setContentType("text/xml");
                    return responseXml;
                }
            }
        }
        //失败应答
        resMap.put("return_code", "FAIL");
        resMap.put("return_msg", "");
        String responseXml = WXPayUtil.mapToXml(resMap);
        response.setContentType("text/xml");

        return responseXml;
    }

}
