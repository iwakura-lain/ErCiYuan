package cn.antigenmhc;

import cn.antigenmhc.otaku.service.manager.AdminApplication;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayInputStream;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/3 23:27
 * @Version: 1.0
 **/
@SpringBootTest(classes = {AdminApplication.class})
public class OSSTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
    // RAM 账号的 accessKey
    String accessKeyId = "LTAI4GHuKKmNvRD7yMynyc2R";
    String accessKeySecret = "5FmTCHVQjvzz4yocGnGUk2246FKXYY";
    //已经创建了的
    String bucketName = "antigenmhc-erciyuan";

    // 创建OSSClient实例。
    OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

    @Test
    public void test(){

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "C:\\Users\\72349\\Pictures\\Camera Roll\\vs_back\\4}YA$`Z1GVYRLZ424V{`G}O.png";
        String content = "hello oss";

        //如果不存在，才创建 bucket
        if (!ossClient.doesBucketExist(bucketName)) {
            ossClient.createBucket(bucketName);
        }

        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));

        // 关闭OSSClient。
        ossClient.shutdown();
    }


}
