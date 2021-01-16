package cn.antigenmhc.otaku.service.manager.pojo;

import cn.antigenmhc.otaku.service.base.pojo.BasePojo;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("otaku_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment extends BasePojo {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "动漫id")
    private String animeId;

    @ApiModelProperty(value = "管理员id")
    private String adminId;

    @ApiModelProperty(value = "用户id")
    private String memberId;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    private String content;

}
