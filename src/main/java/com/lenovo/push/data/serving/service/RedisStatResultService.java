package com.lenovo.push.data.serving.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lenovo.lps.push.common.stat.GroupCounter;
import com.lenovo.lps.push.common.stat.GroupCounter.CounterUnit;
import com.lenovo.push.data.serving.entity.StatResultEntity;
import com.lenovo.push.data.serving.util.DateUtil;

@Service("redisStatResultService")
public class RedisStatResultService {
	static Logger logger = Logger.getLogger(RedisStatResultService.class);
	
	//private final static ShardedRedisUtil SRU = new ShardedRedisUtil(RedisInstance.REDIS_PUSH_DATA);
	private static final GroupCounter COUNTER = new GroupCounter();
	
	public StatResultEntity getDailyStatResult(String thekey, String thedate) {
		StatResultEntity sre = new StatResultEntity();
		//sre.setThedate(thedate);
		//String value = SRU.get(thekey + "." + thedate);
		//if (value == null) {
		//	value = "0";
		//}
		int value = COUNTER.getCnt(thekey, CounterUnit.DAY, DateUtil.dateCompletion(thedate));
		sre.setValue(value);
		logger.debug("thekey: " + thekey + ", thedate: " + DateUtil.dateCompletion(thedate) + ", value: " + value);
		return sre;
	}
}
