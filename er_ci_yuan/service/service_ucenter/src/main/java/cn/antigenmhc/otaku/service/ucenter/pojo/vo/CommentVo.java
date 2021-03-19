package cn.antigenmhc.otaku.service.ucenter.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: antigenMHC
 * @Date: 2021/3/19 9:35
 * @Version: 1.0
 **/
@Data
public class CommentVo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String animeId;
    private String userId;
    private String userNickname;
    private String userAvatar;
    private Integer commentNum;
    private String parentCommentId;
    private String toNickname;
    private String comment;
    private String gmtCreate;
    int level;
    private List<CommentVo> childrenComments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if(this == o) {
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        CommentVo that = (CommentVo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
