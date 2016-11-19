package com.lenovo.push.data.serving.entity;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AvazuList extends BaseJsonEntity {
	private List<AvazuEntity> list;
	
	public AvazuList(List<AvazuEntity> list) {
		super();
		this.setList(list);
	}

	public List<AvazuEntity> getList() {
		return list;
	}

	public void setList(List<AvazuEntity> list) {
		this.list = list;
	}
}
