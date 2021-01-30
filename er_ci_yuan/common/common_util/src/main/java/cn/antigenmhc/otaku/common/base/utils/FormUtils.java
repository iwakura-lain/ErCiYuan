package cn.antigenmhc.otaku.common.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: antigenMHC
 * @Date: 2021/1/29 17:40
 * @Version: 1.0
 **/
public class FormUtils {

    // 验证手机号
    private static final Pattern PATTERN = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");

    /**
     * 手机号验证
     */
    public static boolean isMobile(String str) {
        Matcher m = PATTERN.matcher(str);
        return m.matches();
    }

}
