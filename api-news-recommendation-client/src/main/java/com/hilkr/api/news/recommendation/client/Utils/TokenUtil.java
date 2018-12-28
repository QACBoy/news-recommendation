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
    public static String generateToken(String key, String username) {
        String time = DataUtil.getTimeStr();
        String str = time + "&" + username + "&" + key;
        byte[] resBytes = Base64.encodeBase64(str.getBytes());
        String res = new String(resBytes);
        log.info(" ****** [ TokenUtil-generateToken:String] 生成的 token 为：{}", res);
        return res;
    }

    public static boolean checkTokenOwnerIsRigth(Token tokenObj, String username) {
        String tokenUsername = tokenObj.getUsername();
        Boolean res = tokenUsername.equals(username);
        if (res) {
            log.info(" ****** [ TokenUtil-checkToken:Boolean] token 拥有者验证 成功 ，传入拥有者为 [{}] ，实际拥有者为 [{}] ！", username, tokenUsername);
        } else {
            log.info(" ****** [ TokenUtil-checkToken:Boolean] token 拥有者验证 失败 ，传入拥有者为 [{}] ，实际拥有者为 [{}] ！", username, tokenUsername);
        }
        return res;
    }

    public static boolean checkTokenIsTimeOut(Token tokenObj) {
        long tokenTime = Long.valueOf(tokenObj.getTime());
        long nowTime = DataUtil.getTime();
        int outTime = 10;
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

    public static Token tokenStr2Obj(String tokenStr) throws Exception{
        byte[] tokenBytes = Base64.decodeBase64(tokenStr);
        String token = new String(tokenBytes);
        log.info(" ****** [ TokenUtil-tokenStr2Obj:Token] 解密结果为：{}", token);
        String res = String.valueOf(token);
        String[] tokenArray = res.split("&");
        Token newToken = new Token(tokenArray[0], tokenArray[1], tokenArray[2]);
        log.info(" ****** [ TokenUtil-tokenStr2Obj:Token] 转换结果为：{}", newToken.toString());
        return newToken;
    }

    public static void main(String[] args) {
        String key = "979877512b6938b84c0ef9c27aa5da9a";
        // String token = generateToken(key);
        String tokenStr = "MTU0NTkyOTcxMDE4NCY5Nzk4Nzc1MTJiNjkzOGI4NGMwZWY5YzI3YWE1ZGE5YQ==";
        Token tokenObj = null;
        try {
            tokenObj = tokenStr2Obj(tokenStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkTokenIsTimeOut(tokenObj);
        // generateNewToken(token);
    }
}
