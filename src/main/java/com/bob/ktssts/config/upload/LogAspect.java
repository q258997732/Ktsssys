package com.bob.ktssts.config.upload;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static com.bob.ktssts.util.upload.LogUtils.logToFile;


@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 日志切面
     * @param joinPoint
     * @param ex
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* cn.attackme.myuploader.*.*.*(..)))")
    public void logPoint(JoinPoint joinPoint, Throwable ex) {
        logToFile(joinPoint,ex);
    }
}
