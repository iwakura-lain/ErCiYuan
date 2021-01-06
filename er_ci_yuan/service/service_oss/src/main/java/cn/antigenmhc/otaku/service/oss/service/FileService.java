package cn.antigenmhc.otaku.service.oss.service;

import java.io.InputStream;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/5 21:32
 * @Version: 1.0
 **/
public interface FileService {
    /**
     * 文件上传接口
     * @param inputStream：文件输入流
     * @param dir：上传到 bucket 的哪个目录
     * @param originFilename：文件的原始名字
     * @return：文件的 url
     */
    String upload(InputStream inputStream, String dir, String originFilename);
}
