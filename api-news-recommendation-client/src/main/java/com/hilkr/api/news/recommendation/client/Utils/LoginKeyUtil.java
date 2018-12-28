package com.hilkr.api.news.recommendation.client.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/27
 *
 */
@Slf4j
public class LoginKeyUtil {

    public static String generateLoginKey(String username, String password, String time) {
        String md5Str = username + "&" + password + "&" + "hilkr" + "&" + time;
        String res = DigestUtils.md5Hex(md5Str);
        log.info(" ****** [ LoginKeyUtil-generateLoginKey:String ] 生成的 key 为：{}", res);
        return res;
    }

    public static boolean checkLoginKey(String username, String password, String time, String token) {
        Boolean res = token.equals(generateLoginKey(username, password, time));
        log.info(" ****** [ LoginKeyUtil-checkLoginKey:Boolean ] 判断结果为：{}", res);
        return res;
    }

    public static void main(String[] args) {
        String name = "hilkr";
        String password = "123456";
        String time = DataUtil.getTimeStr();
        generateLoginKey(name, password, time);
    }
}
