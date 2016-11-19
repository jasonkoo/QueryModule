package com.lenovo.push.data.serving.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.data.serving.dao.AvazuDao;
import com.lenovo.push.data.serving.entity.AvazuEntity;
import com.lenovo.push.data.serving.mapper.AvazuMapper;

@Repository("mysqlAvazuDao")
public class AvazuDaoImpl extends BaseDaoImpl<AvazuEntity, AvazuMapper> implements AvazuDao {
	
	public AvazuDaoImpl() {
		setMapperClass(AvazuMapper.class);
	}
	
	public AvazuEntity getDailyAvazuFeedbackStat(String thekey, String thedate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thekey", thekey);
		params.put("thedate", thedate);
		return this.getMapper().getDailyAvazuFeedbackStat(params);
	}

	public List<AvazuEntity> getDailyAvazuErrorStat(String thekey,
			String thedate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thekey", thekey);
		params.put("thedate", thedate);
		return this.getMapper().getDailyAvazuErrorStat(params);
	}

}
