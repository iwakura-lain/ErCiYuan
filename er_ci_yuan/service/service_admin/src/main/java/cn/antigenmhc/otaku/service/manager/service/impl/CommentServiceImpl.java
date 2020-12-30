package cn.antigenmhc.otaku.service.manager.service.impl;

import cn.antigenmhc.otaku.service.manager.pojo.Comment;
import cn.antigenmhc.otaku.service.manager.mapper.CommentMapper;
import cn.antigenmhc.otaku.service.manager.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2020-11-30
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
