package com.hilkr.api.news.recommendation.client.Utils;

import org.apache.commons.codec.digest.DigestUtils;
import sun.tools.jstat.Token;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/26
 *
 */
public class TokenUtils {

    public static String getToken(String name, String password) {
        String md5Str = name + password;
        String res = DigestUtils.md5Hex(md5Str);
        System.out.println(" MD5 加密结果为：" + res);
        return res;
    }

    public static boolean checkToken(String name, String password, String token) {
        return token.equals(getToken(name, password));
    }
}
