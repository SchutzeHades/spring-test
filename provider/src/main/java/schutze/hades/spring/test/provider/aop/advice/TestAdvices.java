package schutze.hades.spring.test.provider.aop.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class TestAdvices {

    @Around("schutze.hades.spring.test.provider.aop.pointcut.TestPointcuts.inTaskLayer()")
    public Object inTaskLayerLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Advise around task layer out of pointcut class.");
        return pjp.proceed();
    }

    @Around("schutze.hades.spring.test.provider.aop.pointcut.TestPointcuts.testService()")
    public Object serviceBeansLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Advise around TestService out of pointcut class.");
        return pjp.proceed();
    }
}
