package omega.lang.log;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.LogFactory;

public class Log   {

	private org.apache.commons.logging.Log logger;

	private static ConcurrentHashMap<Object, Log> logMap = new ConcurrentHashMap<Object, Log>();

	public static Log build(Class clazz) {
		Log log = new Log();
		log.setLogger(LogFactory.getLog(clazz));
		return log;
	}

	public static Log getLog(Class clazz) {

		Log log = logMap.get(clazz);
		if (log == null) {
			log = build(clazz);
			logMap.putIfAbsent(clazz, log);
		}
		return log;
	}

	public org.apache.commons.logging.Log getLogger() {
		return logger;
	}

	public void setLogger(org.apache.commons.logging.Log logger) {
		this.logger = logger;
	}
 

	public void debug(Object message) {
		logger.debug(message);

	}
	public void debug(Object message, Throwable t) {
		logger.debug(message,t);
	}

	public void info(Object message) {
		logger.info(message);

	}

	public void info(Object message, Throwable t) {
		logger.info(message,t);
	}
	
	public void warn(Object message) {
		logger.warn(message);

	}

	public void warn(Object message, Throwable t) {
		logger.warn(message,t);
	}

	public void error(Object message) {
		logger.error(message);
	}

	public void error(Object message, Throwable t) {
		logger.error(message,t);

	}

	public void fatal(Object message) {
		logger.fatal(message);

	}

	public void fatal(Object message, Throwable t) {
		logger.fatal(message,t);
	}

}
