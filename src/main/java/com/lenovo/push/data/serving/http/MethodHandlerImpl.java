package com.lenovo.push.data.serving.http;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.lenovo.push.data.serving.entity.AvazuEntity;
import com.lenovo.push.data.serving.entity.AvazuList;
import com.lenovo.push.data.serving.entity.BaseJsonEntity;
import com.lenovo.push.data.serving.entity.StartupArgsEntity;
import com.lenovo.push.data.serving.entity.StatResultEntity;
import com.lenovo.push.data.serving.entity.StatResultList;
import com.lenovo.push.data.serving.service.MysqlAvazuService;
import com.lenovo.push.data.serving.service.MysqlStatResultService;
import com.lenovo.push.data.serving.service.RedisStatResultService;
import com.lenovo.push.data.serving.util.DateUtil;
import com.lenovo.push.data.serving.util.ParamUtil;

@Component("methodHandlerImpl")
public class MethodHandlerImpl implements MethodHandler {
	
	static Logger logger = Logger.getLogger(MethodHandlerImpl.class);

	@Resource(name = "mysqlStatResultService")
	private MysqlStatResultService mysqlStatResultService;
	
	@Resource(name = "redisStatResultService")
	private RedisStatResultService redisStatResultService;
	
	@Resource(name = "mysqlAvazuService")
	private MysqlAvazuService mysqlAvazuService;
	
	@Resource(name = "startupArgs")
	private StartupArgsEntity startupArgs;

	public BaseJsonEntity handleMethod(String methodName,
			List<NameValuePair> params, HttpBody body) {

		BaseJsonEntity result = null;
		try {
			Class<? extends MethodHandlerImpl> classThis = this.getClass();
			Method method = classThis.getDeclaredMethod(methodName,
					new Class[] { List.class, HttpBody.class });
			Object[] args = new Object[]{params, body};
			result = (BaseJsonEntity) method.invoke(this, args);

		} catch (InvocationTargetException ex) {
			result = new BaseJsonEntity();
			result.setMessage(ex.getTargetException().getMessage());
		} catch (NoSuchMethodException ex) {
			result = new BaseJsonEntity();
			result.setMessage("no such method: " + methodName);
		} catch (Exception ex) {
			result = new BaseJsonEntity();
			result.setMessage("internal error occurred: please contact the admin");
		}
		return result;

	}

	@SuppressWarnings("unused")
	private BaseJsonEntity getA(List<NameValuePair> params, HttpBody body) {
		// only for testing, return "a received";
		BaseJsonEntity result = new BaseJsonEntity();
		result.setMessage("a received");
		return result;
	}
	
	@SuppressWarnings("unused")
	private BaseJsonEntity getDailyStatResult(List<NameValuePair> params, HttpBody body) throws UnsupportedEncodingException {
		String thekey = ParamUtil.getParameter(params, "thekey");
		logger.debug("thekey: " + thekey);
		String thedate = thekey.substring(thekey.lastIndexOf(".") + 1, thekey.length());
		logger.debug("thedate: " + thedate);
		thekey = thekey.substring(0, thekey.lastIndexOf("."));
		
		logger.debug("switch hour diff: " + startupArgs.getHourDiff());
		
		// Should check the format of thedate first
		//if (thedate.length() != 8 && !thedate.matches(regex))
		
		if (thedate.equals(DateUtil.getToday())) {
			return redisStatResultService.getDailyStatResult(thekey, thedate);
		} else {
			if (thedate.equals(DateUtil.getYesterday()) && DateUtil.getHourOfDay() < startupArgs.getHourDiff()) {
				return redisStatResultService.getDailyStatResult(thekey, thedate);
			} else {
				return mysqlStatResultService.getDailyStatResult(thekey, thedate);
			}
		}
	}
	
	@SuppressWarnings("unused")
	private BaseJsonEntity getSummaryStatResult(List<NameValuePair> params, HttpBody body) throws UnsupportedEncodingException {
		String thekey = ParamUtil.getParameter(params, "thekey");
		/*StatResultEntity sre = new StatResultEntity();
		int sum = 0;
		if (DateUtil.getHourOfDay() < startupArgs.getHourDiff()) {
			sum += mysqlStatResultService.getStatResultByDateRange(thekey, DateUtil.getEpochDate(), DateUtil.getNBeforeDate(2)).getValue();
			sum += redisStatResultService.getDailyStatResult(thekey, DateUtil.getYesterday()).getValue();
			sum += redisStatResultService.getDailyStatResult(thekey, DateUtil.getToday()).getValue();
			sre.setValue(sum);
		} else {
			sum += mysqlStatResultService.getSummaryStatResult(thekey).getValue();
			sum += redisStatResultService.getDailyStatResult(thekey, DateUtil.getToday()).getValue();
			sre.setValue(sum);
		}
		return sre;
		*/
		StatResultEntity sreMysql = mysqlStatResultService.getSummaryStatResult(thekey);
		StatResultEntity sreRedis = redisStatResultService.getDailyStatResult(thekey, DateUtil.getToday());
		logger.debug("thekey: " + thekey + ", mysql: " + sreMysql.getValue() + ", redis: " + sreRedis.getValue());
		sreMysql.setValue(sreMysql.getValue() + sreRedis.getValue());
		logger.debug(thekey + ", mysql + redis: " + sreMysql.getValue());
		return sreMysql;
		
		
	}

	@SuppressWarnings("unused")
	private BaseJsonEntity getDailyAvazuFeedbackStat(List<NameValuePair> params, HttpBody body) throws UnsupportedEncodingException {
		String thekey = ParamUtil.getParameter(params, "thekey");
		logger.debug("thekey: " + thekey);
		String thedate = thekey.substring(thekey.lastIndexOf(".") + 1, thekey.length());
		logger.debug("thedate: " + thedate);
		thekey = thekey.substring(0, thekey.lastIndexOf("."));
		return mysqlAvazuService.getDailyAvazuFeedbackStat(thekey, thedate);
	}
	
	@SuppressWarnings("unused")
	private BaseJsonEntity getDailyAvazuErrorStat(List<NameValuePair> params, HttpBody body) throws UnsupportedEncodingException {
		String thekey = ParamUtil.getParameter(params, "thekey");
		logger.debug("thekey: " + thekey);
		String thedate = thekey.substring(thekey.lastIndexOf(".") + 1, thekey.length());
		logger.debug("thedate: " + thedate);
		thekey = thekey.substring(0, thekey.lastIndexOf("."));
		List<AvazuEntity> list = mysqlAvazuService.getDailyAvazuErrorStat(thekey, thedate);
		return new AvazuList(list);
	}
}
