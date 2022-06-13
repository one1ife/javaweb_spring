package com.xyxy.util;
/**
 * @Author Jacky Zou
 * @Date 2022/5/9 16:14
 * @Version 1.0
 */
public class StringUtil {
    //判断字符串是否为null或“”
    public static boolean isEmpty(String s){
        return s == null || "".equals(s);
    }

    public static boolean isNotEnpty(String s){
        return !isEmpty(s);
    }


}
