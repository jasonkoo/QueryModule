package com.lenovo.push.data.serving.http;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.push.data.serving.util.JsonUtil;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HttpBody {

	private List<String> pnl;

	public List<String> getPnl() {
		return pnl;
	}

	public void setPnl(List<String> pnl) {
		this.pnl = pnl;
	}
	
	public static HttpBody parse(String jonStr) throws JsonParseException, JsonMappingException, IOException{
		return (HttpBody) JsonUtil.jsonString2Entity(jonStr, HttpBody.class);
	}
}
