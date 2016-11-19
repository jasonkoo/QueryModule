package com.lenovo.push.data.serving.http;

import java.net.URI;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

import com.lenovo.push.data.serving.entity.BaseJsonEntity;
import com.lenovo.push.data.serving.util.ParamUtil;

@Component("engineHttpRequestHandlerImpl")
public class EngineHttpRequestHandlerImpl implements EngineHttpRequestHandler {

	static Logger logger = Logger.getLogger(EngineHttpServerHandler.class);

	@Resource(name = "methodHandlerImpl")
	private MethodHandler methodHandler;

	public BaseJsonEntity handleHttpRequest(HttpRequest httpRequest)
			throws Exception {
		String uri = httpRequest.getUri();
		logger.debug("URI: " + uri);

		if (StringUtils.isEmpty(uri) || !uri.startsWith("/engine")) {
			throw new URIException("Unsupported URI: " + uri);
		}
		List<NameValuePair> params = URLEncodedUtils.parse(new URI(uri), "UTF-8");
		
		/*
		HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), httpRequest);
		List<InterfaceHttpData> dataList = decoder.getBodyHttpDatas();//.getBodyHttpData("fromField1");
		if (dataList!=null) {
			for (InterfaceHttpData data : dataList ) {
				if (data.getHttpDataType() == HttpDataType.Attribute) {
				     Attribute attribute = (Attribute) data;
				     String name = attribute.getName();
				     String value = attribute.getValue();
				     //System.out.println("fromField1 :" + value);
				  }
			}
		}
		*/
		  
		String method = ParamUtil.getParameter(params, "method");
		
		ChannelBuffer cb = httpRequest.getContent();
		HttpBody body = null;
		if (cb.readable()) {
		    String json = cb.toString(CharsetUtil.UTF_8);
		    logger.debug("body: " + json);
		    body = HttpBody.parse(json);
		}

		// if (methodHandler == null) {
		// methodHandler = new MethedHandlerImpl();
		// }

		return methodHandler.handleMethod(method, params, body);

	}

}
