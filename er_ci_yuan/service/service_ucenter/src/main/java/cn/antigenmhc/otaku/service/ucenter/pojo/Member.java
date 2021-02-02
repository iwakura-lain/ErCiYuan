package cn.antigenmhc.otaku.service.ucenter.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author antigenmhc
 * @since 2021-01-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ucenter_member")
@ApiModel(value="Member对象", description="会员表")
public class Member extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "微信openid")
    private String openid;

    @ApiModelProperty(value = "github id")
    private String githubId;

    @ApiModelProperty(value = "github id")
    private String giteeId;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别 1 男，2 女")
    private Integer sex;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户签名")
    private String sign;

    @ApiModelProperty(value = "是否禁用 1（true）已禁用，  0（false）未禁用")
    @TableField("is_disabled")
    private Boolean disabled;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;
}
