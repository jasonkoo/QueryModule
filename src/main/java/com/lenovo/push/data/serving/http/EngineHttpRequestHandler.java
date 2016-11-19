package com.lenovo.push.data.serving.http;

import org.jboss.netty.handler.codec.http.HttpRequest;

import com.lenovo.push.data.serving.entity.BaseJsonEntity;

public interface EngineHttpRequestHandler {

	public BaseJsonEntity handleHttpRequest(HttpRequest httpRequest) throws Exception;
}
