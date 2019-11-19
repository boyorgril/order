package com.groupwork.order.aop;

import com.groupwork.order.datasource.dto.OperationRecord;
import com.groupwork.order.service.OperationRecordService;
import com.groupwork.order.utils.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Aspect
@Component
public class LogAop {

    @Autowired
    private OperationRecordService recordService;

    @Pointcut("execution(* com.groupwork.order.service.*.*(..)) && !execution(* com.groupwork.order.service.OperationRecordService.*(..)) ")
    public void operationLog(){}

    @After("operationLog()")
    public void after(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        String describe = "调用后的方法为：" + methodName + ",参数为：" + args;
        OperationRecord record = new OperationRecord();
        record.setDescribe(describe);
        record.setIpAddress(IpUtil.ipAddress);
        record.setCreateAt(new Date());
        recordService.saveRecord(record);

    }

    @AfterThrowing(value="operationLog()", throwing="ex")
    public void afterReturning(JoinPoint point, Exception ex){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        String describe = "调用的方法为：" + methodName + ",参数为：" + args + ",异常为：" +ex ;
        OperationRecord record = new OperationRecord();
        record.setDescribe(describe);
        record.setIpAddress(IpUtil.ipAddress);
        record.setCreateAt(new Date());
        recordService.saveRecord(record);
    }


}
