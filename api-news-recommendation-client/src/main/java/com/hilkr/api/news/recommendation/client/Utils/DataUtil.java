package com.hilkr.api.news.recommendation.client.Utils;

import lombok.extern.slf4j.Slf4j;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/27
 *
 */
@Slf4j
public class DataUtil {
    public static long getTime(){
        long time = System.currentTimeMillis();
        log.info(" ****** [ DataUtil-getTime:long ] 获取当前时间戳：{}", time);
        return time;
    }
    public static String getTimeStr(){
        String time= String.valueOf(System.currentTimeMillis());
        log.info(" ****** [ DataUtil-getTime:String ] 获取当前时间戳：{}", time);
        return time;
    }
}
