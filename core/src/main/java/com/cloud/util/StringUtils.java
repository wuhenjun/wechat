package com.cloud.util;

public class StringUtils {
    /**
     * 检验字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return null == str ? false : "".equals(str) ? false : true;
    }
}

