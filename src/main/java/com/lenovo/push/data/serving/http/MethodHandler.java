package com.lenovo.push.data.serving.http;

import java.util.List;

import org.apache.http.NameValuePair;

import com.lenovo.push.data.serving.entity.BaseJsonEntity;


public interface MethodHandler {

	public BaseJsonEntity handleMethod(String method, List<NameValuePair> params, HttpBody body) throws Exception;

}
