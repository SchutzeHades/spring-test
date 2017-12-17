package schutze.hades.spring.test.provider.task;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TestTask {

    @Value("${sentence}")
    private String sentence;

    @Scheduled(fixedRate = 1000L)
    public void speak() {
        log.info(sentence);
    }
}
