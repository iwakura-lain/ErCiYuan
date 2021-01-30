package cn.antigenmhc.otaku.service.sms.controller.api;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.sms.service.SmsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:48
 * @Version: 1.0
 **/
@Api(description = "短信管理")
@RestController
@RequestMapping("/api/site/sms")
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @GetMapping("send/{mobile}")
    public Result getCheckCode(@PathVariable("mobile") String mobile){
        return smsService.sendCheckCode(mobile);
    }
}
