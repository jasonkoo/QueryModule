package com.lenovo.push.data.serving.dao;

import com.lenovo.push.data.serving.entity.StatResultEntity;
import com.lenovo.push.data.serving.mapper.StatResultMapper;

public interface StatResultDao extends BaseDao<StatResultEntity, StatResultMapper> {	
	public StatResultEntity getDailyStatResult(String thekey, String thedate);
	public StatResultEntity getSummaryStatResult(String thekey);
	public StatResultEntity getStatResultByDateRange(String thekey, String startdate, String enddate);
}
