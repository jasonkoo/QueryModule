package com.lenovo.push.data.serving.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.data.serving.dao.AvazuDao;
import com.lenovo.push.data.serving.entity.AvazuEntity;

@Service("mysqlAvazuService")
public class MysqlAvazuService {
	
	@Autowired
	private AvazuDao avazuDao;
	
	public AvazuEntity getDailyAvazuFeedbackStat(String thekey, String thedate) {
		AvazuEntity ae = avazuDao.getDailyAvazuFeedbackStat(thekey, thedate);
		if (ae == null) {
			return new AvazuEntity(0);
		}
		return ae;
	}
	
	public List<AvazuEntity> getDailyAvazuErrorStat(String thekey, String thedate) {
		List<AvazuEntity> list = avazuDao.getDailyAvazuErrorStat(thekey, thedate);
		if (list == null) {
			return new ArrayList<AvazuEntity>();
		}
		return list;
	}

}
