package schutze.hades.spring.test.provider.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import schutze.hades.spring.test.provider.service.TestService;

@Slf4j
@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    @SneakyThrows
    @Async
    public void asyncTest() {
        while (true) {
            log.info("Async operation.");
            Thread.sleep(5000L);
        }
    }
}
