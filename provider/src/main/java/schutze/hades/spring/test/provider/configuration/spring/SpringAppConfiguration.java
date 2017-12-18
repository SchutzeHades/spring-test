package schutze.hades.spring.test.provider.configuration.spring;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import lombok.val;

@Configuration
@ComponentScan(basePackages = "schutze.hades.spring.test.provider", //
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^schutze\\.hades\\.spring\\.test\\.provider\\.(web|configuration)\\..+$"))
@Import({ //
        SpringSchedulingConfiguration.class, //
        SpringAsyncConfiguration.class //
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringAppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws IOException {
        val result = new PropertySourcesPlaceholderConfigurer();
        result.setLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:*.properties"));
        result.setIgnoreUnresolvablePlaceholders(true);
        return result;
    }
}
