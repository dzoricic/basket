package hr.abysalto.hiring.mid.client;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class DummyJsonApiClientAspect {

    @Before("execution(* hr.abysalto.hiring.mid.client.DummyJsonApiClient.*(..))")
    public void logRequest(JoinPoint joinPoint) {
        var args = joinPoint.getArgs();
        var method = joinPoint.getSignature().getName();
        log.info("[DummyJsonApiClient] Calling method {} with arguments {}", method, args);
    }
}
