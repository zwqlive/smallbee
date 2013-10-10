package com.smallbee.common.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.smallbee.script.IScriptEngine;
import com.smallbee.script.JSScriptManagerImpl;

/**
 * 配置相关的工具类
 * 
 * 
 */
public class ConfigUtil {
	private static final Logger logger = LogManager
			.getLogger(ConfigUtil.class);

	/**
	 * 根据指定的配置类型<tt>configClass</tt>从<tt>configURL</tt>中加载配置
	 * 
	 * @param <T>
	 * @param configClass
	 *            配置的类型
	 * @param configURL
	 *            配置文件的URL,文件内容是一个以JavaScript编写的配置脚本
	 * @return 从configURL加载的配置对象
	 * @exception RuntimeException
	 *                从configClass构造对象失败时抛出此异常
	 * @exception IllegalArgumentException
	 *                配置验证失败时抛出此异常
	 * @exception IllegalStateException
	 *                从configUrl中加载内容失败时抛出此异常
	 */
	public static <T extends Config> T buildConfig(Class<T> configClass,
			URL configURL) {
		if (configClass == null) {
			throw new IllegalArgumentException("configClass");
		}
		if (configURL == null) {
			throw new IllegalArgumentException("configURL");
		}
		if (logger.isInfoEnabled()) {
			logger.info("Load config [" + configClass + "] from [" + configURL
					+ "]");
		}
		T _config = null;
		try {
			_config = configClass.newInstance();
		} catch (InstantiationException e1) {
			throw new RuntimeException(e1);
		} catch (IllegalAccessException e1) {
			throw new RuntimeException(e1);
		}
		IScriptEngine _jsEngine = new JSScriptManagerImpl("UTF-8");
		Map<String, Object> _bindings = new HashMap<String, Object>();
		_bindings.put("config", _config);
		Reader _r = null;
		String _scriptContent = null;
		try {
			_r = new InputStreamReader(configURL.openStream(), "UTF-8");
			_scriptContent = IOUtils.toString(_r);
		} catch (IOException e) {
			throw new IllegalStateException("Can't load config from url ["
					+ configURL + "]");
		} finally {
			IOUtils.closeQuietly(_r);
		}
		_jsEngine.runScript(_bindings, _scriptContent);
		_config.validate();
		return _config;
	}

	/**
	 * 获得配置文件的真实路径
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getConfigPath(String fileName) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return classLoader.getResource(fileName).getPath();
	}

	public static URL getConfigURL(String fileName) {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		return classLoader.getResource(fileName);
	}

	public static void loadJsConfigsByOrder(String[] configs)
			throws ScriptException, IOException {
		Reader reader = null;
		ScriptEngine engine = new ScriptEngineManager()
				.getEngineByName("JavaScript");
		for (String cfgFile : configs) {
			try {
				reader = new FileReader(System.getProperty("user.dir")
						+ File.separator + cfgFile);
				engine.eval(reader);
			} catch (IOException e) {
				throw e;
			} finally {
				if (reader != null) {
					reader.close();
				}
			}
		}
	}

	public static void loadJsConfig(boolean isEncrypt, Config config,
			String cfgFile) throws ScriptException, IOException {
		logger.info(String.format("Load js config, file: %s", cfgFile));
		Reader reader = null;
		URL cfgUrl = config.getClass().getClassLoader().getResource(cfgFile);
		try {

			if (cfgUrl != null) {
				InputStream in = null;
				if (isEncrypt) {					
					//TODO:解密
//					in = new ByteArrayInputStream(cfgFile.getBytes());
				} else {
					in = cfgUrl.openStream();
				}
				reader = new InputStreamReader(in, "UTF-8");
			} else {
				if (isEncrypt) {
					//TODO:解密
//					reader = new InputStreamReader(new InputStream(
//							cfgFile));
				} else {
					reader = new FileReader(System.getProperty("user.dir")
							+ File.separator + cfgFile);
				}

			}
			ScriptEngine engine = new ScriptEngineManager()
					.getEngineByName("JavaScript");
			Map<String, Object> bindings = new HashMap<String, Object>();
			bindings.put("config", config);
			engine.eval(reader, new SimpleBindings(bindings));
		} catch (IOException e) {
			logger.error(String.format("Load js config error, file: %s",
					cfgFile));
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		config.validate();
	}

	public static void loadJsConfig(Config config, String cfgFile)
			throws ScriptException, IOException {
		loadJsConfig(false, config, cfgFile);
	}

}
