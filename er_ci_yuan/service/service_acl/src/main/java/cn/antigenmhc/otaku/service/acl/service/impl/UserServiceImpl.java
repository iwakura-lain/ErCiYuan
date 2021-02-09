package cn.antigenmhc.otaku.service.acl.service.impl;

import cn.antigenmhc.otaku.service.acl.mapper.UserMapper;
import cn.antigenmhc.otaku.service.acl.pojo.User;
import cn.antigenmhc.otaku.service.acl.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        return baseMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }
}
