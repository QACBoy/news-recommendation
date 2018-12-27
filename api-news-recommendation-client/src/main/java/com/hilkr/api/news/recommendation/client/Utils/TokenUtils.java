package com.hilkr.api.news.recommendation.client.Utils;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class TokenUtils {

    public static String getToken(String name, String password) {
        String md5Str = name + password;
        String res = DigestUtils.md5Hex(md5Str);
        log.info(" MD5 加密结果为：{}", res);
        return res;
    }

    public static boolean checkToken(String name, String password, String token) {
        return token.equals(getToken(name, password));
    }

    public static void main(String[] args) {
        String name = "hilkr";
        String password = "123456";
        String token = getToken(name, password);
        log.info("getToken : {}", token);

    }
}
