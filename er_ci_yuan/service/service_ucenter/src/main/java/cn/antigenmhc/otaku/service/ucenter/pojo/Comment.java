package cn.antigenmhc.otaku.service.ucenter.pojo;

import cn.antigenmhc.otaku.service.ucenter.pojo.vo.CommentVo;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author antigenmhc
 * @since 2021-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ucenter_comment")
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "admin ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "动漫id")
    private String animeId;

    @ApiModelProperty(value = "评论者id")
    private String userId;

    @ApiModelProperty(value = "评论者昵称")
    private String userNickname;

    @ApiModelProperty(value = "评论者头像")
    private String userAvatar;

    @ApiModelProperty(value = "回复次数")
    private Integer commentNum;

    @ApiModelProperty(value = "父评论")
    private String parentCommentId;

    @ApiModelProperty(value = "被回复人昵称")
    private String toNickname;

    @ApiModelProperty(value = "回复内容")
    private String comment;

    @TableField(exist = false)
    private List<CommentVo> childrenComments = new ArrayList<>();
}
