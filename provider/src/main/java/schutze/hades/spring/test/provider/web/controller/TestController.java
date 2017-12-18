package schutze.hades.spring.test.provider.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import schutze.hades.spring.test.provider.service.TestService;

@RestController
@Slf4j(topic = "businessExceptionLogger")
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping
    public String getTest() {
        testService.asyncTest();
        log.warn("Well, just suppose I'm a business exception.");
        return "Hello World!";
    }
}
