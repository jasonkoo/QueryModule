package com.lenovo.push.data.serving;

import java.io.File;

import org.apache.log4j.Logger;

import com.lenovo.push.data.serving.util.ConfigUtil;
import com.lenovo.push.data.serving.util.Env;
import com.lenovo.push.data.serving.util.PathUtil;


public class QueryModule 
{	
	private static Logger logger = Logger.getLogger(QueryModule.class);
	
	private static final String log4jFileName = "log4j.xml";
	private static final String springFileName = "applicationContext.xml";
	
    public static void main( String[] args )
    {
    	logger.info("Config log4j...");
    	ConfigUtil.configLog4j(PathUtil.getModuleConfDir() + File.separator + log4jFileName);
    	logger.info("Config log4j done!");
    	
    	logger.info("Config Spring...");
    	if (Env.isLinux()) {
        	ConfigUtil.configSpring(File.separator + PathUtil.getModuleConfDir() + File.separator + springFileName);

    	} else {
        	ConfigUtil.configSpring(PathUtil.getModuleConfDir() + File.separator + springFileName);

    	}
    	logger.info("Initialize Spring ok!");
    }
}
