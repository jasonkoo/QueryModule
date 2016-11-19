package com.lenovo.push.data.serving.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.data.serving.dao.StatResultDao;
import com.lenovo.push.data.serving.entity.StatResultEntity;
import com.lenovo.push.data.serving.mapper.StatResultMapper;

@Repository("mysqlStatResultDao")
public class StatResultDaoImpl extends BaseDaoImpl<StatResultEntity, StatResultMapper> implements
		StatResultDao {
	
	public StatResultDaoImpl() {
		setMapperClass(StatResultMapper.class);
	}

	public StatResultEntity getDailyStatResult(String thekey, String thedate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thekey", thekey);
		params.put("thedate", thedate);
		return this.getMapper().getDailyStatResult(params);
	}
	
	public StatResultEntity getSummaryStatResult(String thekey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thekey", thekey);
		return this.getMapper().getSummaryStatResult(params);
	}
	
	public StatResultEntity getStatResultByDateRange(String thekey, String startdate, String enddate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thekey", thekey);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		return this.getMapper().getStatResultByDateRange(params);
	}

	
}
