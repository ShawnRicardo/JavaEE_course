package com.example.api;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class ApiMonitoringAspect {
    private Map<String, ApiStats> apiStatsMap = new ConcurrentHashMap<>();

    @Pointcut("@annotation(com.example.api.ApiMonitor)")
    public void apiMonitored() {
    }

    @Around("apiMonitored()")
    public Object monitorApi(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().toShortString();

        try {
            Object result = joinPoint.proceed();
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            // 更新统计信息
            updateApiStats(methodName, executionTime, false);

            return result;
        } catch (Exception e) {
            // 发生异常时更新异常次数统计
            updateApiStats(methodName, 0, true);
            throw e;
        }
    }

    private synchronized void updateApiStats(String methodName, long executionTime, boolean hasError) {
        ApiStats apiStats = apiStatsMap.get(methodName);

        if (apiStats == null) {
            apiStats = new ApiStats();
            apiStatsMap.put(methodName, apiStats);
        }

        apiStats.incrementCallCount();
        apiStats.updateExecutionTime(executionTime);
        if (hasError) {
            apiStats.incrementErrorCount();
        }
    }

    public Map<String, ApiStats> getApiStatsMap() {
        return apiStatsMap;
    }
}
