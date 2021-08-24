package com.zx.simpleSpring.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parseToObject(String jsonStr, Class<T> tClass) throws JsonProcessingException {
        return mapper.readValue(jsonStr, tClass);
    }

    public static byte[] toByte(Object o) {
        byte[] bytes = new byte[0];
        try {
            bytes = mapper.writeValueAsBytes(o);
        } catch (JsonProcessingException ignored) {
        }
        return bytes;
    }

    public static <T> T convertObject(Object o, Class<T> targetType) {
        return mapper.convertValue(o, targetType);
    }
}
