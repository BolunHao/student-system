package com.management.aspect;

import com.management.Parametric.OperateRecord;
import com.management.controller.BaseController;
import com.management.exception.MyException;
import com.management.service.services.AuthorityService;
import com.management.service.services.OperateRecordService;
import com.management.service.services.RoleAuthorityService;
import com.management.utils.EnumCode;
import com.management.utils.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
public class HttpAspect extends BaseController {

    @Autowired
    private RoleAuthorityService RoleAuthorityService;

    @Autowired
    private AuthorityService AuthorityService;

    @Autowired
    private OperateRecordService operatingRecordService;

    private final static Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.management.controller..*(..))")
    public void log() {

    }
    /**
     * @desc: Requests for recording
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        shiroFilter(joinPoint);
    }

    /**
     * @desc: Responding to requests
     */
    @After("log()")
    public void doAfter() {
        log.info("========================== ↓Responding to requests↓ ==========================");
    }

    /**
     * @desc: Print the return value
     */
    @AfterReturning(returning = "obj",pointcut = "log()")
    public void doAfterReturnning(Object obj) {
        log.info("Request Return Value：{}",obj);
    }


    /**
     * @desc: Unified Parameter Validation Processing
     */
    @Around("execution(* com.management.controller..*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {

        shiroFilter(pjp);

        Object retVal;
        if (bindingResult.hasErrors()) {
            return ResultUtil.result(EnumCode.BAD_REQUEST.getValue(),bindingResult.getFieldError().getDefaultMessage(),null);
        } else {
            retVal = pjp.proceed();
        }
        return retVal;
    }

    /**
     * @desc: request interceptor
     */
    public void shiroFilter(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String requestUrl = request.getRequestURI().replaceAll(request.getContextPath(), "");
        String remoteAddr = request.getRemoteAddr();
        String method = request.getMethod();
        String args = Arrays.toString(joinPoint.getArgs());

        log.info("========================== ↓Request received↓ ==========================");
        log.info("Request url:{}",requestUrl);
        log.info("Request source ip:{}",remoteAddr);
        log.info("request method:{}",method);
        log.info("Request method:{}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+ "()");
        log.info("request parameter:{}", args);
        log.info("getContextPath:{}",request.getContextPath());
        log.info("========================== ↑Request received↑ ==========================");

        OperateRecord or = new OperateRecord();
        or.setRequestUrl(requestUrl);
        or.setRemoteAddr(remoteAddr);
        or.setMethod(method);
        or.setCreateTime(new Date());
        or.setUserId(super.getUserId());

        //Below is the permission verification based on the requested url
        Integer count = AuthorityService.findCountByUrl(requestUrl);
        //If the requested url exists in the database, it means that it has permissions, otherwise no permissions are required
        if (count != 0){
            String roleId = super.getRoleId();
            if (StringUtils.isEmpty(roleId)) {
                or.setIsSuccess(0);
                operatingRecordService.insert(or);
                throw new MyException(ResultUtil.result(EnumCode.FORBIDDEN.getValue(), EnumCode.FORBIDDEN.getText()));
            }

            Integer row = RoleAuthorityService.findCountByRole(roleId, request.getRequestURI().replaceAll(request.getContextPath(),""));
            //If the current role has no permissions and is not an administrator, role ‘1’ is the administrator,
            // which can be adjusted according to the situation,
            // since the administrator does not have all permissions.
            if (row == 0 && ! "1".equals(super.getRoleId())) {
                or.setIsSuccess(0);
                operatingRecordService.insert(or);
                throw new MyException(ResultUtil.result(EnumCode.FORBIDDEN.getValue(), EnumCode.FORBIDDEN.getText()));
            }
        }
        or.setIsSuccess(1);
        operatingRecordService.insert(or);
    }
}
