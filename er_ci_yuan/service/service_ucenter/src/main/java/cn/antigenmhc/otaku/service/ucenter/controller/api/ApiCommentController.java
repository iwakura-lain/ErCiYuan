package cn.antigenmhc.otaku.service.ucenter.controller.api;


import cn.antigenmhc.otaku.common.base.result.Result;
import cn.antigenmhc.otaku.service.ucenter.pojo.Comment;
import cn.antigenmhc.otaku.service.ucenter.pojo.vo.CommentVo;
import cn.antigenmhc.otaku.service.ucenter.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author antigenmhc
 * @since 2021-03-19
 */
@RestController
@RequestMapping("/api/ucenter/comment")
public class ApiCommentController {

    private final CommentService commentService;

    public ApiCommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation("添加评论")
    @PostMapping("auth/add-comment")
    public Result addComment(@RequestBody Comment comment){
        commentService.save(comment);
        return Result.ok().setMessage("评论成功");
    }

    @ApiOperation("获取评论")
    @GetMapping("get-comment-list/{animeId}")
    public Result getComments(@PathVariable("animeId") String animeId){
        List<CommentVo> commentsList = commentService.getAllComments(animeId);
        return Result.ok().setData("items", commentsList);
    }
}

