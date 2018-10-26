package com.zl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhuolin
 * @program: cooperator
 * @date 2018/10/25
 * @description: ${description}
 **/
public class StringUtils {
    /**
     * 判断字符串是否为空
     *
     * @param string 字符串
     * @return 是否为空
     */
    public static Boolean isBlank(String string) {
        return string == null || string.length() == 0 || string.trim().length() == 0;
    }

    /**
     * 判断字符串是非空
     *
     * @param string 字符串
     * @return 是否非空
     */
    public static Boolean isNotBlank(String string) {
        return string != null && string.length() != 0 && string.trim().length() != 0;
    }

    /**
     * 判断是否为手机号
     *
     * @param string 字符串
     * @return 是否为手机号
     */
    public static Boolean isMobile(String string) {
        if (isBlank(string)) {
            return false;
        }
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String phoneRegex = "[1][0123456789]\\d{9}";
        return string.matches(phoneRegex);
    }

    /**
     * 是否为固定电话
     *
     * @param string
     * @return
     */
    public static Boolean isFixedPhone(String string) {
        String reg = "(?:(\\(\\+?86\\))(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)|" +
                "(?:(86-?)?(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?)";
        return Pattern.matches(reg, string);
    }

    /**
     * 验证Email格式
     */
    public static boolean isEmail(String strEmail) {
        if (isBlank(strEmail)) {
            return false;
        }

        String strPattern = "[a-zA-Z0-9_-]+@\\w+\\.[a-z]+(\\.[a-z]+)?";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);

        return m.matches();
    }
}
