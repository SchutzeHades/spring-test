package schutze.hades.spring.test.provider.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import schutze.hades.spring.test.provider.service.TestService;

@Log4j2
@Service("testService")
public class TestServiceImpl implements TestService {

    @Override
    @SneakyThrows
    @Async
    public void asyncTest() {
        while (true) {
            log.info("Async operation.");
            log.warn("Pretend to be an exception.");
            Thread.sleep(5000L);
        }
    }
}
