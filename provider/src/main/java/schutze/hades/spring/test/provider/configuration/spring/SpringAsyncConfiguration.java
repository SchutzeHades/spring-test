package schutze.hades.spring.test.provider.configuration.spring;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.val;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class SpringAsyncConfiguration implements AsyncConfigurer {

    @Override
    @Bean("taskExecutor")
    public Executor getAsyncExecutor() {
        val result = new ThreadPoolTaskExecutor();
        result.setCorePoolSize(10);
        result.setMaxPoolSize(10);
        result.setKeepAliveSeconds(0);
        result.setQueueCapacity(100);
        result.setWaitForTasksToCompleteOnShutdown(true);
        result.setAwaitTerminationSeconds(10);
        result.setThreadNamePrefix("custom-task-executor");
        return result;
    }
}
