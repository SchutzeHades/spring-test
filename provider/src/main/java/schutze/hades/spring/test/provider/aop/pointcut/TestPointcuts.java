package schutze.hades.spring.test.provider.aop.pointcut;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Aspect
public class TestPointcuts {

    @Pointcut("within(schutze.hades.spring.test.provider.task..*)")
    public void inTaskLayer() {
    }

    @Pointcut("execution(* schutze.hades.spring.test.provider.service.TestService.*(..))")
    public void testService() {
    }

    @Around("inTaskLayer()")
    public Object inTaskLayerLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("Advise around task layer in pointcut class.");
        return pjp.proceed();
    }
}
