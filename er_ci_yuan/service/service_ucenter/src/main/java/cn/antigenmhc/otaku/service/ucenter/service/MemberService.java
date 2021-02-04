package cn.antigenmhc.otaku.service.ucenter.service;

import cn.antigenmhc.otaku.service.base.dto.MemberDto;
import cn.antigenmhc.otaku.service.ucenter.pojo.Member;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.LoginVo;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.RegisterVo;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.UpdateVo;
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

    String login(LoginVo loginVo);

    void registerByOauth2(RegisterVo registerVo, String oauthId, String type);

    MemberDto getMemberDtoByMemberId(String memberId);

    String updateMemberInfo(UpdateVo updateVo);
}
