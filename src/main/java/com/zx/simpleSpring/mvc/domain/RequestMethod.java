package com.zx.simpleSpring.mvc.domain;

import io.netty.handler.codec.http.HttpMethod;
import lombok.Data;

import java.util.Map;

/**
 * 请求方法统一处理封装实体类
 */
@Data
public class RequestMethod {
    // url
    private String url;
    // 方法名
    private HttpMethod method;
    // get 请求参数
    private Map<String, String> paramGet;
    // post 请求参数
    private Object paramPost;

    public RequestMethod(String uri, Map<String, String> res) {
        this.url = uri;
        this.paramGet = res;
    }

    public RequestMethod(String uri) {
        this.url = uri;
    }
}
