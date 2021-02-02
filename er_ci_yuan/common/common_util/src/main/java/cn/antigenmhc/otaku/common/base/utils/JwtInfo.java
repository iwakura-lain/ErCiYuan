package cn.antigenmhc.otaku.common.base.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/30 11:55
 * @Version: 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtInfo {
    private String id;
    private String nickname;
    private String avatar;
    //权限、角色等
    //不要存敏感信息
}
