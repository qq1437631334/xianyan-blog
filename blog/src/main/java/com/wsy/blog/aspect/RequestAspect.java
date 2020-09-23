package com.wsy.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.wsy.blog.constant.Const;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.pojo.Log;
import com.wsy.blog.service.LogService;
import com.wsy.blog.utils.StringUtils;
import com.wsy.blog.utils.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 切面输出基本信息
 * 以及记录日志
 *
 * @author: wsy
 * @date: 2020/8/9 13:22
 */
@Aspect
@Component
@Slf4j
public class RequestAspect {

    @Autowired
    LogService logService;

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     */
    @Pointcut("execution( * com.wsy.*.controller..*(..))")
    public void logPointCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
        Log logger = ThreadLocalContext.get().getLogger();
        //默认设置为正常状态
        logger.setLogStatus(StateEnum.REQUEST_SUCCESS.getCode());
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);
        //日志查询不记录到日志里
        if(!Const.LOG_PAGE_URL.equals(logger.getLogUrl())){
            logService.save(logger);
        }
        return ob;
    }

    /**
     * 后置通知
     *
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        String result = JSON.toJSONString(ret);
        log.info("返回值：{}", JSON.toJSONString(ret));
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogResult(JSON.toJSONString(ret));
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "logPointCut()", throwing = "e")
    public void saveExceptionLog(JoinPoint joinPoint, Throwable e) {
        Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogStatus(StateEnum.REQUEST_ERROR.getCode());
        String exception = StringUtils.getPackageException(e, "com.wsy");
        logger.setLogMessage(exception);
        logService.save(logger);
    }

    /**
     * 打印请求日志
     *
     * @param joinPoint
     * @param request
     * @param uri
     */
    private void printRequestLog(JoinPoint joinPoint, HttpServletRequest request, String uri) {
        // 拿到切面方法
        log.info("请求地址 : {}", uri);
        log.info("请求方式 : {}", request.getMethod());
        // 获取真实的ip地址
        String ip = StringUtils.getRemoteIp(request);
        log.info("IP : {}", ip);
        String controllerName = joinPoint.getSignature().getDeclaringTypeName();
        log.info("方法 : {}.{}", controllerName, joinPoint.getSignature().getName());
        String params = Arrays.toString(joinPoint.getArgs());
        //如果字段中有密码将密码设置为空
//        if(params.contains("password")) {
//
//        }
        log.info("请求参数：{}", params);
        // 获取日志实体
        Log logger = ThreadLocalContext.get().getLogger();

        logger.setLogUrl(uri);
        logger.setLogMethod(request.getMethod());
        logger.setLogIp(ip);
        logger.setLogParams(params);
    }

}
