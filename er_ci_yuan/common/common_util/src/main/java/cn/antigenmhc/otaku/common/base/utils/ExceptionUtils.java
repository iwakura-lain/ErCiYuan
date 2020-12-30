package cn.antigenmhc.otaku.common.base.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author: antigenMHC
 * @Date: 2020/12/10 23:06
 * @Version: 1.0
 * 封装错误栈信息及格式
 **/
public class ExceptionUtils {
    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace(pw);
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
