package cn.antigenmhc.otaku.service.acl.service;

import cn.antigenmhc.otaku.service.acl.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-02-07
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户信息
     */
    User selectByUsername(String username);
}
