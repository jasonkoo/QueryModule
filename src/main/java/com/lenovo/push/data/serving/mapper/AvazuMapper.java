package com.lenovo.push.data.serving.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.data.serving.entity.AvazuEntity;

public interface AvazuMapper extends BaseMapper<AvazuEntity> {
	public AvazuEntity getDailyAvazuFeedbackStat(Map<String, Object> params);
	public List<AvazuEntity> getDailyAvazuErrorStat(Map<String, Object> params);
}
