package cn.antigenmhc.otaku.service.ucenter.service.impl;

import cn.antigenmhc.otaku.service.ucenter.mapper.CommentMapper;
import cn.antigenmhc.otaku.service.ucenter.pojo.Comment;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.CommentVo;
import cn.antigenmhc.otaku.service.ucenter.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author antigenmhc
 * @since 2021-03-19
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public List<CommentVo> getAllComments(String animeId) {
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("anime_id", animeId).orderByDesc("id");
        List<Comment> commentsList = this.list(queryWrapper);

        List<CommentVo> tree = new ArrayList<>();
        for (Comment comment : commentsList) {
            CommentVo commentVo = new CommentVo();
            BeanUtils.copyProperties(comment, commentVo);
            commentVo.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(comment.getGmtCreate()));
            tree.add(commentVo);
        }

        List<CommentVo> list = getList(tree);
        for (CommentVo commentVo : list) {
            mergeChildren(commentVo);
        }
        list.sort((o1, o2) -> o2.getGmtCreate().compareTo(o1.getGmtCreate()));
        return list;
    }

    private List<CommentVo> getList(List<CommentVo> comments){
        List<CommentVo> trees = new ArrayList<>();
        for (CommentVo comment : comments) {
            if ("0".equals(comment.getParentCommentId())){
                comment.setLevel(0);
                trees.add(findAllChildren(comment, comments));
            }
        }
        return trees;
    }

    private CommentVo findAllChildren(CommentVo treeNode, List<CommentVo> treeNodes) {
        treeNode.setChildrenComments(new ArrayList<>());

        for (CommentVo it : treeNodes) {
            if(treeNode.getId().equals(it.getParentCommentId())) {
                int level = treeNode.getLevel() + 1;
                List<CommentVo> childrenComments = treeNode.getChildrenComments();
                it.setLevel(level);

                if (childrenComments == null) {
                    treeNode.setChildrenComments(new ArrayList<>());
                }
                childrenComments.add(findAllChildren(it, treeNodes));
            }
        }

        return treeNode;
    }

    /**
     * 数组打平
     * @param comment：根评论
     */
    private void mergeChildren(CommentVo comment){
        LinkedList<CommentVo> deque = new LinkedList<>();
        deque.push(comment);
        List<CommentVo> tmp = new ArrayList<>();

        while (!deque.isEmpty()){
            int size = deque.size();
            while (size > 0){
                CommentVo commentVo = deque.poll();
                List<CommentVo> childrenComments = commentVo.getChildrenComments();
                for (CommentVo childrenComment : childrenComments) {
                    deque.push(childrenComment);
                    tmp.add(childrenComment);
                }
                size--;
            }
        }
        for (CommentVo commentVo : tmp) {
            if(!"0".equals(commentVo.getParentCommentId())){
                commentVo.setChildrenComments(new ArrayList<>());
            }
        }
        comment.setChildrenComments(new ArrayList<>(tmp));
    }
}
