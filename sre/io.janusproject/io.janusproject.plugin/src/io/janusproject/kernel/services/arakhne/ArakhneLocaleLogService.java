/*
 * $Id$
 *
 * SARL is an general-purpose agent programming language.
 * More details on http://www.sarl.io
 *
 * Copyright (C) 2014-2016 the original authors or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.janusproject.kernel.services.arakhne;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.google.common.base.Strings;
import com.google.common.util.concurrent.Service;
import com.google.inject.Inject;
import io.janusproject.JanusConfig;
import io.janusproject.services.AbstractDependentService;
import io.janusproject.services.logging.LogService;
import org.arakhne.afc.vmutil.ClassLoaderFinder;
import org.arakhne.afc.vmutil.locale.Locale;

/**
 * This class enables to log information by ensuring that the values of the parameters are not evaluated until the information
 * should be really log, according to the log level. This implementation is based on {@link Locale}, and the logger is injected.
 *
 * <p>The LogService considers the parameters of the functions as:
 * <ul>
 * <li>the messageKey is the name of the message in the property file;</li>
 * <li>the message parameters are the values that will replace the strings {0}, {1}, {2}... in the text extracted from the
 * resource property;</li>
 * <li>the parameter propertyType is the class from which the filename of the property file will be built.</li>
 * </ul>
 *
 * <p>If a <code>Throwable</code> is passed as parameter, the text of the exception is retrieved.
 *
 * <p>If a <code>Callable</code> is passed as parameter, the object is automatically called.
 *
 * <p>If a <code>LogParam</code> is passed as parameter, the <code>toString</code> function will be invoked.
 *
 * <p>For all the other objects, the {@link #toString()} function is invoked.
 *
 *
 * @author $Author: sgalland$
 * @version $FullVersion$
 * @mavengroupid $GroupId$
 * @mavenartifactid $ArtifactId$
 */
public class ArakhneLocaleLogService extends AbstractDependentService implements LogService {

	private Logger logger;

	private LoggerCallerProvider loggerCallerProvider = new StackTraceLoggerCallerProvider();

	/**
	 * Construct the service.
	 */
	public ArakhneLocaleLogService() {
		//
	}

	/**
	 * Replies the object that permits to determine the caller of the logger.
	 *
	 * @return the object that permits to determine the caller of the logger.
	 */
	public LoggerCallerProvider getLoggerCaller() {
		return this.loggerCallerProvider;
	}

	/**
	 * Change the object that permits to determine the caller of the logger.
	 *
	 * @param provider - the object that permits to determine the caller of the logger.
	 */
	public void setLoggerCaller(LoggerCallerProvider provider) {
		if (provider == null) {
			this.loggerCallerProvider = new StackTraceLoggerCallerProvider();
		} else {
			this.loggerCallerProvider = provider;
		}
	}

	@Override
	public final Class<? extends Service> getServiceType() {
		return LogService.class;
	}

	/**
	 * Replies if this service permits to log the messages.
	 *
	 * @return <code>true</code> if the messages are loggeable, <code>false</code> otherwise.
	 */
	protected boolean isLogEnabled() {
		return state().ordinal() <= State.RUNNING.ordinal();
	}

	private static String getLogRecordText(LoggerCaller caller, Class<?> propertyType, String messageKey, Object... message) {
		Class<?> propType = propertyType;
		if (propType == null) {
			propType = caller.getPropertyType();
		}
		if (propType == null) {
			throw new IllegalStateException();
		}
		return Locale.getString(propType, messageKey, message);
	}

	private static LogRecord createLogRecord(Level level, String text, boolean exception, Object... message) {
		Throwable realException = null;
		if (exception) {
			for (final Object m : message) {
				if (m instanceof Throwable) {
					realException = (Throwable) m;
					break;
				}
			}
		}
		final LogRecord record = new LogRecord(level, text);
		if (realException != null) {
			record.setThrown(realException);
		}

		if (record.getLoggerName() == null) {
		    record.setLoggerName(JanusConfig.JANUS_DEFAULT_PLATFORM_NAME);
		}
		return record;
	}

	private synchronized void writeInLog(Level level, boolean exception, Class<?> propertyType, String messageKey,
			Object... message) {
		if (isLogEnabled() && this.logger.isLoggable(level)) {
			final LoggerCaller caller = this.loggerCallerProvider.getLoggerCaller();
			final String text = getLogRecordText(caller, propertyType, messageKey, message);
			final LogRecord record = createLogRecord(level, text, exception, message);
			record.setSourceClassName(caller.getTypeName());
			final String methodName = caller.getMethod();
			if (!Strings.isNullOrEmpty(methodName)) {
				record.setSourceMethodName(methodName);
			}
			this.logger.log(record);
		}
	}

	@Override
	public synchronized void log(LogRecord record) {
		if (isLogEnabled()) {
			this.logger.log(record);
		}
	}

	@Override
	public void log(Level level, Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(level, true, propertyType, messageKey, message);
	}

	@Override
	public void log(Level level, String messageKey, Object... message) {
		writeInLog(level, true, null, messageKey, message);
	}

	@Override
	public void info(String messageKey, Object... message) {
		writeInLog(Level.INFO, false, null, messageKey, message);
	}

	@Override
	public void info(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.INFO, false, propertyType, messageKey, message);
	}

	@Override
	public void fineInfo(String messageKey, Object... message) {
		writeInLog(Level.FINE, false, null, messageKey, message);
	}

	@Override
	public void fineInfo(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.FINE, false, propertyType, messageKey, message);
	}

	@Override
	public void finerInfo(String messageKey, Object... message) {
		writeInLog(Level.FINER, false, null, messageKey, message);
	}

	@Override
	public void finerInfo(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.FINER, false, propertyType, messageKey, message);
	}

	@Override
	public void debug(String messageKey, Object... message) {
		writeInLog(Level.FINEST, true, null, messageKey, message);
	}

	@Override
	public void debug(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.FINEST, true, propertyType, messageKey, message);
	}

	@Override
	public void warning(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.WARNING, true, propertyType, messageKey, message);
	}

	@Override
	public void warning(String messageKey, Object... message) {
		writeInLog(Level.WARNING, true, null, messageKey, message);
	}

	@Override
	public void error(String messageKey, Object... message) {
		writeInLog(Level.SEVERE, true, null, messageKey, message);
	}

	@Override
	public void error(Class<?> propertyType, String messageKey, Object... message) {
		writeInLog(Level.SEVERE, true, propertyType, messageKey, message);
	}

	@Override
	public synchronized Logger getLogger() {
		return this.logger;
	}

	@Inject
	@Override
	public synchronized void setLogger(Logger logger) {
		if (logger != null) {
			this.logger = logger;
		}
	}

	@Override
	public synchronized void setFilter(Filter filter) {
		this.logger.setFilter(filter);
	}

	@Override
	public synchronized Filter getFilter() {
		return this.logger.getFilter();
	}

	@Override
	public boolean isLoggeable(Level level) {
		return isLogEnabled() && this.logger.isLoggable(level);
	}

	@Override
	public Level getLevel() {
		return this.logger.getLevel();
	}

	@Override
	public void setLevel(Level level) {
		this.logger.setLevel(level);
	}

	@Override
	protected void doStart() {
		notifyStarted();
	}

	@Override
	protected void doStop() {
		notifyStopped();
	}

	/** Find a class.
	 *
	 * @param className the name of the class.
	 * @return the class, or <code>null</code>.
	 */
	protected static Class<?> findClass(String className) {
		final ClassLoader loader = ClassLoaderFinder.findClassLoader();
		Class<?> type = null;
		try {
			type = Class.forName(className);
		} catch (ClassNotFoundException e) {
			//
		}
		if (type == null && loader != null) {
			try {
				type = loader.loadClass(className);
			} catch (ClassNotFoundException e) {
				//
			}
		}
		if (type != null && !LogService.class.isAssignableFrom(type)
				&& !LoggerCallerProvider.class.isAssignableFrom(type)) {
			return type;
		}
		return null;
	}

	/**
	 * Provides the type of the caller of the logger.
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public interface LoggerCallerProvider {

		/**
		 * Replies the logger caller.
		 *
		 * @return the logger caller.
		 */
		LoggerCaller getLoggerCaller();

	}

	/**
	 * Provides the type of the caller of the logger.
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class LoggerCaller {

		private Class<?> type;

		private final String methodName;

		private final String className;

		/**
		 * @param className - the type of the caller.
		 * @param methodName - the name of the called method.
		 */
		public LoggerCaller(String className, String methodName) {
			this(null, className, methodName);
		}

		/**
		 * @param propertyType - the type for which property file must be retreived.
		 * @param className - the type of the caller.
		 * @param methodName - the name of the called method.
		 */
		public LoggerCaller(Class<?> propertyType, String className, String methodName) {
			this.type = propertyType;
			this.className = className;
			this.methodName = methodName;
		}

		/**
		 * Replies the type of the logger caller.
		 *
		 * @return the type of the logger caller.
		 */
		public Class<?> getPropertyType() {
			if (this.type == null) {
				this.type = findClass(getTypeName());
				if (this.type == null) {
					this.type = Object.class;
				}
			}
			return this.type;
		}

		/**
		 * Replies the name of the type of the logger caller.
		 *
		 * @return the name of type of the logger caller.
		 */
		public String getTypeName() {
			return this.className;
		}

		/**
		 * Replies the name of the last method encountered in the stack trace.
		 *
		 * @return the name of the last invoked method of the logger caller.
		 */
		public String getMethod() {
			return this.methodName;
		}

	}

	/**
	 * Provider of calling function on the stack trace.
	 *
	 * @author $Author: sgalland$
	 * @version $FullVersion$
	 * @mavengroupid $GroupId$
	 * @mavenartifactid $ArtifactId$
	 */
	public static class StackTraceLoggerCallerProvider implements LoggerCallerProvider {

		/**
		 * Construct.
		 */
		public StackTraceLoggerCallerProvider() {
			//
		}

		private static StackTraceElement getStackTraceElement() {
			final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
			// Start at 1 because the top of the stack corresponds to getStackTrace.
			for (int i = 1; i < stackTrace.length; ++i) {
				final String className = stackTrace[i].getClassName();
				final Class<?> type = findClass(className);
				if (type != null) {
					return stackTrace[i];
				}
			}
			return null;
		}

		@Override
		public LoggerCaller getLoggerCaller() {
			final StackTraceElement element = getStackTraceElement();
			if (element != null) {
				return new LoggerCaller(element.getClassName(), element.getMethodName());
			}
			return null;
		}

	}

}
