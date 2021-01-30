package cn.antigenmhc.otaku.service.ucenter.service.impl;

import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.FormUtils;
import cn.antigenmhc.otaku.common.base.utils.MD5;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.mapper.MemberMapper;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-01-29
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private RedisUtil redisUtil;
    @Value("${member.defaultAvatar}")
    private String defaultAvatar;

    @Override
    public void register(RegisterVo registerVo) {
        if(checkParams(registerVo)){
            String nickname = registerVo.getNickname();
            String mobile = registerVo.getMobile();
            String password = registerVo.getPassword();
            String code = registerVo.getCode();
            //获取验证码并校验
            String checkCode = (String)redisUtil.get(registerVo.getMobile());
            if(!code.equals(checkCode)){
                throw new IntegrateException(ResultCodeEnum.CODE_ERROR);
            }
            //判断用户是否已被注册(根据手机号判断)
            QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mobile", mobile);
            if(baseMapper.selectOne(queryWrapper) != null){
                throw new IntegrateException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
            }
            Member member = new Member();
            member.setNickname(nickname);
            member.setMobile(mobile);
            member.setPassword(MD5.encrypt(password));
            member.setAvatar(defaultAvatar);
            member.setDisabled(false);
            baseMapper.insert(member);
        }
    }

    private boolean checkParams(RegisterVo registerVo){
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        if(StringUtils.isEmptyOrWhitespaceOnly(mobile) || !FormUtils.isMobile(mobile)){
            throw new IntegrateException("请确认手机号是否正确", 28039);
        }

        if(StringUtils.isEmptyOrWhitespaceOnly(nickname)
                || StringUtils.isEmptyOrWhitespaceOnly(password)
                || StringUtils.isEmptyOrWhitespaceOnly(code)){
            throw new IntegrateException("警告！请填写全部选项", 28049);
        }
        return true;
    }
}
