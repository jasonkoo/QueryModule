package com.lenovo.push.data.serving.mapper;

import java.util.Map;

import com.lenovo.push.data.serving.entity.StatResultEntity;

public interface StatResultMapper extends BaseMapper<StatResultEntity> {
	public StatResultEntity getDailyStatResult(Map<String, Object> params);
	public StatResultEntity getSummaryStatResult(Map<String, Object> params);
	public StatResultEntity getStatResultByDateRange(Map<String, Object> params);
}
