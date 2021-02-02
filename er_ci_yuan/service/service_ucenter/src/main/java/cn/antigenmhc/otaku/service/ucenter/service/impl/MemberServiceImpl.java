package cn.antigenmhc.otaku.service.ucenter.service.impl;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.FormUtils;
import cn.antigenmhc.otaku.common.base.utils.JwtInfo;
import cn.antigenmhc.otaku.service.ucenter.utils.JwtUtil;
import cn.antigenmhc.otaku.common.base.utils.MD5;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.base.utils.RedisUtil;
import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.mapper.MemberMapper;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.LoginVo;
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

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验
        if(StringUtils.isEmptyOrWhitespaceOnly(mobile) || !FormUtils.isMobile(mobile)){
            throw new IntegrateException("请确认手机号格式是否正确", 28039);
        }
        if(StringUtils.isEmptyOrWhitespaceOnly(password)){
            throw new IntegrateException("请输入密码", 28059);
        }
        //获取信息，校验手机号是否存在
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if(member == null){
            throw new IntegrateException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        //校验密码
        if(!MD5.encrypt(password).equals(member.getPassword())){
            throw new IntegrateException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //检查用户是否处于禁言状态
        if(member.getDisabled()){
            throw new IntegrateException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //登录，设置有效荷载的用户信息，生成 token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtil.getJwtToken(jwtInfo, 1800);
        redisUtil.set(member.getId(), jwtToken,60*60*24*10);
        return jwtToken;
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
