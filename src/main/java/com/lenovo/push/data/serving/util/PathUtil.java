package com.lenovo.push.data.serving.util;

import java.io.File;

public class PathUtil {
	private static String MODULE_HOME;
	private final static String CONF_DIR_NAME = "conf";

	public static String getModuleConfDir() {
		return getModuleHomeDir() + File.separator + PathUtil.CONF_DIR_NAME;
	}
	
	public static String getModuleHomeDir() {
		if (MODULE_HOME == null) {
			MODULE_HOME = System.getProperty("module.home");
		}
		if (MODULE_HOME == null) {
			throw new RuntimeException("module.home is null");
		}
		return MODULE_HOME;
	}
}
