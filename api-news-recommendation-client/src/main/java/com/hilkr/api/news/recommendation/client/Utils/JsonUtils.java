package com.hilkr.api.news.recommendation.client.Utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/***
 *
 * 描述：
 *
 * @author sam
 * @date 2018/12/27
 *
 */
public class JsonUtils {

    public static Object json2Obj(Object obj,String jsonStr) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return obj = mapper.readValue(jsonStr, obj.getClass());
    }

    public static String obj2Json(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
