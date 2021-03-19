package cn.antigenmhc.otaku.service.ucenter.service;

import cn.antigenmhc.otaku.service.ucenter.pojo.Comment;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.CommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-03-19
 */
public interface CommentService extends IService<Comment> {

    List<CommentVo> getAllComments(String animeId);
}
