package schutze.hades.spring.test.provider.web.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import schutze.hades.spring.test.provider.service.TestService;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping
    public String getTest() {
        testService.asyncTest();
        return "Hello World!";
    }
}
