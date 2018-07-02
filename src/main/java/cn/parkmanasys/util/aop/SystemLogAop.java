package cn.parkmanasys.util.aop;

import java.net.URLDecoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.parkmanasys.dao.SysOperationLogMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.SysOperationLog;
import cn.parkmanasys.service.OperationRecord.SysOperationLogService;
import cn.parkmanasys.util.annotation.LogTypeEnum;
import cn.parkmanasys.util.annotation.SystemLog;

/**
 * @author linlangleo
 * @date 2018/7/1 15:40
 * @desc 日志管理切面.
 */
@Aspect
@Component
public class SystemLogAop {
	@Resource
	private SysOperationLogMapper sysOperationLogMapper;
	
	//注解切入点
	@Pointcut("@annotation(cn.parkmanasys.util.annotation.SystemLog)")
	public void systemLog(){
		//补充注释
	}
	
	@Around("systemLog() && @annotation(log)")
	public Object remarkLog(ProceedingJoinPoint pjp, SystemLog log) throws Throwable{
		Object proceed = null;
        //获取方法名
        String className = getClassName(pjp);
        //获取路径
        String url = getUrl();
		if(log.logType() == LogTypeEnum.OPERATION_LOG){
			//执行前
			String operationDesc = log.operationDesc();
			logSave(2, operationDesc, className);
			
			//执行方法
			proceed = pjp.proceed();
			//执行后
			return proceed;
		}else if(log.logType() == LogTypeEnum.INTERFACE_LOG){
			
		}
		
		return proceed;
	}
	
	 /**
     * @author linlangleo
     * @desc .日志记录方法
     */
	private void logSave(int userId, String operationDesc, String className){
		//默认操作记录
        String operationType = className;
        //操作内容
        String args = getArgs();
        if (className.startsWith("update")) {
        	operationType = "修改操作";
        }
        if (className.startsWith("delete") || className.startsWith("del")) {
        	operationType = "删除操作";
        }
        if (className.startsWith("save") || className.startsWith("add")) {
        	operationType = "新增操作";
        }
        if (className.startsWith("assign")) {
        	operationType = "分配操作";
        }
        if (className.startsWith("log")) {
        	operationType = "登入登出";
        }
		SysOperationLog soLog = new SysOperationLog();

		ParkingAccount pa = new ParkingAccount();
		pa.setId(userId);
		soLog.setUser(pa);
		soLog.setOperationDesc(operationDesc);
		soLog.setOperationType(operationType);
		soLog.setOperationContent(args);
//		soLog.setOperationPass("");
		soLog.setOperationTime(new Date());
		soLog.setCreationTime(new Date());
		sysOperationLogMapper.saveAndFlush(soLog);
	}
	

    /**
     * @author linlangleo
     * @desc .获取请求路径
     */
    private String getUrl() {
        //访问路径
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request.getRequestURI();
    }
    
    /**
     * @author linlangleo
     * @desc .获取方法名
     */
    private String getClassName(JoinPoint pjp) {
        return pjp.getSignature().getName();
    }


    /**
     * @author linlangleo
     * @desc .请求参数
     */
    private String getArgs() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String queryString = request.getQueryString();
        //获取修改新增关键词消息
//        String a = "";
//        try {
//            String[] split = queryString.split("=");
//            a = split[1];
//            a = URLDecoder.decode(a, "UTF-8");
//        } catch (Exception e) {
//            a = "异常操作(获取不到messge)";
//        }
//        return a;
        return queryString;
    }

}
