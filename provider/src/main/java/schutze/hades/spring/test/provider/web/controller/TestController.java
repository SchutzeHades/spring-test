package schutze.hades.spring.test.provider.web.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import schutze.hades.spring.test.provider.service.TestService;

@Log4j2(topic = "businessExceptionLogger")
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @PostConstruct
    public void init() {
        log.warn("Init {}", () -> TestController.class);
    }

    @GetMapping
    public String getTest() {
        testService.asyncTest();
        log.warn("Well, just suppose I'm a business exception.");
        return "Hello World!";
    }
}
