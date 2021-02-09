package cn.antigenmhc.otaku.service.acl.controller.admin;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.acl.service.AclIndexService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
public class AclIndexController {

    @Resource
    private AclIndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @GetMapping("info")
    public Result info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return Result.ok().setData(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @GetMapping("menu")
    public Result getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return Result.ok().setData("permissionList", permissionList);
    }

    @PostMapping("logout")
    public Result logout(){
        return Result.ok();
    }

}
