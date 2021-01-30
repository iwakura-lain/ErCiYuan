package cn.antigenmhc.otaku.service.ucenter.service;

import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-01-29
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);
}
