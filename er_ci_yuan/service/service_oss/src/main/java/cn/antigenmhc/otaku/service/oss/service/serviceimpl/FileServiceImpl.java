package cn.antigenmhc.otaku.service.oss.service.serviceimpl;

import cn.antigenmhc.otaku.service.oss.service.FileService;
import cn.antigenmhc.otaku.service.oss.utils.OssProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/5 21:37
 * @Version: 1.0
 **/
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String dir, String originFilename) {

        String endPoint = ossProperties.getEndPoint();
        String accesskeyId = ossProperties.getAccesskeyId();
        String accesskeySecret = ossProperties.getAccesskeySecret();
        String bucketName = ossProperties.getBucketName();
        String projectPathName = createFilePathInBucket(dir, originFilename);

        OSS ossClient = new OSSClientBuilder().build(
                                    endPoint,
                                    accesskeyId,
                                    accesskeySecret
        );
        //判断 bucket 是否存在，不存在则创建，且指定权限为公共读
        if(!ossClient.doesBucketExist(bucketName)){
            ossClient.createBucket(bucketName);
            ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        }

        ossClient.putObject(bucketName, projectPathName, inputStream);

        return "https://" + bucketName + "." + endPoint + "/" + projectPathName;
    }

    /**
     * 1.构建文件的 objectName，在 bucket 中的文件其 objectName 其实是路径 +文件名的形式
     * 为了防止同一文件夹下文件过多造成访问上的性能问题，这里使用以日期命名文件夹路径的方式构建 objectName
     * 也可以通过生成随机字符串来生成文件夹并存放，或者根据用户 id 创建文件夹并存放对应用户的图片
     * 2.同时，为了对文件进行分类，
     * 根路径以文件用途进行命名 (比如：avatar 代表头像，cover 代表封面)
     * 最终构建的全路径名类似于：cover/2021/01/23/originFilename
     * 3.对于用户上传的图片，可能其中会有乱七八糟的字符，
     * 故这里统一使用 UUID 生成随机字符串来替代源文件名，只需要与源文件的扩展名拼接即可
     * @param originFilename : 源文件名
     * @return: 拼接后的真正的文件名
     */
    private String createFilePathInBucket(String module, String originFilename){

        //日期工具类获取格式化日期字符串
        String dateFolderString = new DateTime().toString("yyyy/MM/dd");
        //生成新的文件名
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originFilename.substring(originFilename.lastIndexOf("."));
        //构建最终的 projectName
        String finalFolderPath = module + "/" + dateFolderString + "/" + fileName + fileExtension;

        return finalFolderPath;
    }

}
