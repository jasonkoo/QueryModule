package com.lenovo.push.data.serving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.data.serving.dao.StatResultDao;
import com.lenovo.push.data.serving.entity.StatResultEntity;

@Service("mysqlStatResultService")
public class MysqlStatResultService {
	@Autowired
	private StatResultDao mysqlStatResultDao;
	
	public StatResultEntity getDailyStatResult(String thekey, String thedate) {
		StatResultEntity sre = mysqlStatResultDao.getDailyStatResult(thekey, thedate);
		if (sre == null) {
			return new StatResultEntity(0);
		}
		return sre;
	}
	
	public StatResultEntity getSummaryStatResult(String thekey) {
		StatResultEntity sre = mysqlStatResultDao.getSummaryStatResult(thekey);
		if (sre == null) {
			return new StatResultEntity(0);
		}
		return sre;
	}
	
	public StatResultEntity getStatResultByDateRange(String thekey, String startdate, String enddate) {
		return mysqlStatResultDao.getStatResultByDateRange(thekey, startdate, enddate);
	}
	
	
}
