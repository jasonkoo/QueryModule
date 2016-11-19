package com.lenovo.push.data.serving.entity;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class StatResultList extends BaseJsonEntity{
	private List<StatResultEntity> list;
	
	public StatResultList(List<StatResultEntity> list) {
		super();
		this.setList(list);
	}

	public List<StatResultEntity> getList() {
		return list;
	}

	public void setList(List<StatResultEntity> list) {
		this.list = list;
	}
	
	
}
