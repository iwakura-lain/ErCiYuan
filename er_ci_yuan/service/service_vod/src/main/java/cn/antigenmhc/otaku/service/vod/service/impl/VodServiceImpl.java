package cn.antigenmhc.otaku.service.vod.service.impl;

import cn.antigenmhc.otaku.common.base.result.ResultCodeEnum;
import cn.antigenmhc.otaku.common.base.utils.ExceptionUtils;
import cn.antigenmhc.otaku.service.base.exception.IntegrateException;
import cn.antigenmhc.otaku.service.vod.service.VodService;
import cn.antigenmhc.otaku.service.vod.utils.VodDefaultClientUtils;
import cn.antigenmhc.otaku.service.vod.utils.VodProperties;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.mysql.cj.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/22 15:24
 * @Version: 1.0
 **/
@Service
@Slf4j
public class VodServiceImpl implements VodService {

    @Resource
    private VodProperties vodProperties;

    @Override
    public String uploadVideo(String originalFileName, InputStream inputStream) {
        String accessKeyId = vodProperties.getAccesskeyId();
        String accessKeySecret = vodProperties.getAccesskeySecret();
        String title = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String templateGroupId = vodProperties.getTemplateGroupId();
        String workflowId = vodProperties.getWorkflowId();

        UploadStreamRequest request = new UploadStreamRequest(
                accessKeyId,
                accessKeySecret,
                title, originalFileName,
                inputStream);
        //request.setTemplateGroupId(templateGroupId);
        //request.setWorkflowId(workflowId);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        String videoId = response.getVideoId();
        if(StringUtils.isEmptyOrWhitespaceOnly(videoId)){
            log.error("阿里云vod上传失败："+response.getCode()+"--"+response.getMessage());
            throw new IntegrateException(ResultCodeEnum.VIDEO_UPLOAD_ALIYUN_ERROR);
        }

        return videoId;
    }

    @Override
    public boolean deleteVideo(String vodId) {
        //创建服务端
        DefaultAcsClient client = VodDefaultClientUtils.initVodClient(vodProperties.getAccesskeyId(), vodProperties.getAccesskeySecret());

        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(vodId);

        try {
            client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
           log.error(ExceptionUtils.getMessage(e));
           throw new IntegrateException(ResultCodeEnum.VIDEO_DELETE_ALIYUN_ERROR);
        }
    }

    @Override
    public boolean deleteVideos(String vodIds) {
        return false;
    }
}
