package com.hilkr.api.news.recommendation.client.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/26
 *
 */
@Slf4j
public class TokenUtil {

    /**
     * 通过用户登录后获取的 key，生成 带有时间戳的简易 token
     *
     * @param key
     * @return
     */
    public static String generateToken(String key) {
        String time = DataUtil.getTimeStr();
        String str = time + "&" + key;
        byte[] resBytes = Base64.encodeBase64(str.getBytes());
        String res = new String(resBytes);
        log.info(" ****** [ TokenUtil-generateToken:String] 生成的 token 为：{}", res);
        return res;
    }

    public static boolean checkTokenIsTimeOut(String token) {
        Token tokenObj = tokenStr2Obj(token);
        long tokenTime = Long.valueOf(tokenObj.getTime());
        long nowTime = DataUtil.getTime();
        int outTime = 60;
        long time = (nowTime - tokenTime) / 1000;
        Boolean res = time > outTime;
        log.info(" ****** [ TokenUtil-checkTokenIsTimeOut:Boolean] 判断结果为：{}", res);
        if (res) {
            log.info(" ****** [ TokenUtil-checkTokenIsTimeOut:Boolean] token 已过期，过期时长为：{} s", time - outTime);
        } else {
            log.info(" ****** [ TokenUtil-checkTokenIsTimeOut:Boolean] 距离例 token 过期还有：{} s", outTime - time);
        }
        return res;
    }

    public static Token tokenStr2Obj(String token) {
        byte[] tokenBytes = Base64.decodeBase64(token);
        String tokenStr = new String(tokenBytes);
        log.info(" ****** [ TokenUtil-tokenStr2Obj:Token] 解密结果为：{}", tokenStr);
        String res = String.valueOf(tokenStr);
        String[] tokenArray = res.split("&");
        Token newToken = new Token(tokenArray[0], tokenArray[1]);
        log.info(" ****** [ TokenUtil-tokenStr2Obj:Token] 转换结果为：{}", newToken.toString());
        return newToken;
        // return null;
    }

    public static String generateNewToken(String token) {
        Token tokenObj = tokenStr2Obj(token);
        String key = tokenObj.getKey();
        String res = generateToken(key);
        log.info(" ****** [ TokenUtil-generateNewToken:String] 新生成的 token 为：{}", res);
        return res;
    }

    public static void main(String[] args) {
        String key = "979877512b6938b84c0ef9c27aa5da9a";
        // String token = generateToken(key);
        String token = "MTU0NTkyOTcxMDE4NCY5Nzk4Nzc1MTJiNjkzOGI4NGMwZWY5YzI3YWE1ZGE5YQ==";
        checkTokenIsTimeOut(token);
        // generateNewToken(token);
    }
}
