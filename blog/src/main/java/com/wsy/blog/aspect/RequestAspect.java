package com.wsy.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.wsy.blog.constant.Constants;
import com.wsy.blog.enums.StateEnum;
import com.wsy.blog.annotation.Log;
import com.wsy.blog.service.LogService;
import com.wsy.blog.utils.StringUtils;
import com.wsy.blog.utils.ThreadLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
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


    private final LogService logService;

    public RequestAspect(LogService logService) {
        this.logService = logService;
    }

    /**
     * 配置切入点为标注了Log注解的方法
     */
    @Pointcut("@annotation(com.wsy.blog.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 方法执行之前调用
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception {
        Log log = getAnnotationLog(joinPoint);
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String uri = request.getRequestURI();
        // 记录下请求内容
        printRequestLog(joinPoint, request, uri);
        com.wsy.blog.pojo.Log logger = ThreadLocalContext.get().getLogger();
        //默认设置为正常状态
        logger.setLogStatus(StateEnum.REQUEST_SUCCESS.getCode());
        //如果注解不为空则去获取注解中的title
        if(null != log) {
            logger.setLogTitle(log.title());
        }
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        long time = System.currentTimeMillis() - startTime;
        log.info("耗时 : {}", time);
        com.wsy.blog.pojo.Log logger = ThreadLocalContext.get().getLogger();
        logger.setLogTime(time);
        //日志查询不记录到日志里
        if(!Constants.LOG_PAGE_URL.equals(logger.getLogUrl())){
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
        com.wsy.blog.pojo.Log logger = ThreadLocalContext.get().getLogger();
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
        com.wsy.blog.pojo.Log logger = ThreadLocalContext.get().getLogger();
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
        com.wsy.blog.pojo.Log logger = ThreadLocalContext.get().getLogger();

        logger.setLogUrl(uri);
        logger.setLogMethod(request.getMethod());
        logger.setLogIp(ip);
        logger.setLogParams(params);
    }

    /**
     * 是否存在注解，如果存在就获取
     *
     * @param joinPoint
     * @return
     */
    private Log getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
