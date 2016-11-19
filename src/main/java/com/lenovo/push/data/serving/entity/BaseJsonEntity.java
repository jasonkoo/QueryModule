package com.lenovo.push.data.serving.entity;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.lenovo.push.data.serving.util.JsonUtil;

public class BaseJsonEntity {
	private String message;

	public String toJsonString() throws JsonGenerationException,
			JsonMappingException, IOException {
		return JsonUtil.entity2JsonString(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
