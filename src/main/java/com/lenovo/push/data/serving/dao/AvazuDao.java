package com.lenovo.push.data.serving.dao;

import java.util.List;

import com.lenovo.push.data.serving.entity.AvazuEntity;
import com.lenovo.push.data.serving.mapper.AvazuMapper;

public interface AvazuDao extends BaseDao<AvazuEntity, AvazuMapper> {
	public AvazuEntity getDailyAvazuFeedbackStat(String thekey, String thedate);
	public List<AvazuEntity> getDailyAvazuErrorStat(String thekey, String thedate);
}
