package schutze.hades.spring.test.provider.configuration;

import java.nio.charset.Charset;

import com.google.common.base.Charsets;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.Configurator;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.RollingPolicy;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.ContextAwareBase;
import lombok.val;

public class LogbackConfiguration extends ContextAwareBase implements Configurator {

    @Override
    public void configure(LoggerContext loggerContext) {
        loggerContext.putObject("logDir", "logs");
        loggerContext.putObject("projectName", "spring_test");

        val rootLogFileAppender = buildRollingFileAppender(loggerContext, "INFO",
                "${logDir}/${projectName}.%d{yyyy-MM-dd}.log");
        val errorLogAppender = buildRollingFileAppender(loggerContext, "WARN", "${logDir}/error.%d{yyyy-MM-dd}.log");
        val businessExceptionLogAppender = buildRollingFileAppender(loggerContext, "WARN",
                "${logDir}/business_exception.%d{yyyy-MM-dd}.log");
        val consoleAppender = buildConsoleAppender(loggerContext, "INFO");

        val rootLogger = loggerContext.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.addAppender(consoleAppender);
        rootLogger.addAppender(rootLogFileAppender);
        rootLogger.addAppender(errorLogAppender);

        val businessExceptionLogger = loggerContext.getLogger("businessExceptionLogger");
        businessExceptionLogger.addAppender(businessExceptionLogAppender);
    }

    private ConsoleAppender<ILoggingEvent> buildConsoleAppender(Context context, String level) {
        val result = new ConsoleAppender<ILoggingEvent>();
        result.setContext(context);
        result.setEncoder(buildEncoder(context, Charset.forName("GBK")));
        result.addFilter(buildFilter(context, level));
        result.start();
        return result;
    }

    @SuppressWarnings("unchecked")
    private RollingFileAppender<ILoggingEvent> buildRollingFileAppender(Context context, String level,
            String fileNamePattern) {
        val result = new RollingFileAppender<ILoggingEvent>();
        result.setContext(context);
        result.setEncoder(buildEncoder(context, Charsets.UTF_8));
        result.addFilter(buildFilter(context, level));
        result.setRollingPolicy(buildRollingPolicy(context, result, fileNamePattern));
        result.start();
        return result;
    }

    private RollingPolicy buildRollingPolicy(Context context, FileAppender<?> fileAppender, String fileNamePattern) {
        val result = new TimeBasedRollingPolicy<ILoggingEvent>();
        result.setContext(context);
        result.setParent(fileAppender);
        result.setFileNamePattern(fileNamePattern);
        result.start();
        return result;
    }

    private Encoder<ILoggingEvent> buildEncoder(Context context, Charset charset) {
        val result = new PatternLayoutEncoder();
        result.setContext(context);
        result.setCharset(charset);
        result.setPattern("[%d{yyyy-MM-dd HH:mm} %5p %C:%L] %m%n");
        result.start();
        return result;
    }

    private Filter<ILoggingEvent> buildFilter(Context context, String level) {
        val result = new ThresholdFilter();
        result.setContext(context);
        result.setLevel(level);
        result.start();
        return result;
    }
}