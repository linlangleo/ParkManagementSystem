package cn.parkmanasys.util.aop;

import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

import cn.parkmanasys.dao.SysInterfaceManagerDetailsMapper;
import cn.parkmanasys.dao.SysInterfaceManagerMapper;
import cn.parkmanasys.dao.SysOperationLogMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.SysInterfaceManagerDetails;
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
	@Resource
	private SysInterfaceManagerMapper sysInterfaceManagerMapper;
	@Resource
	private SysInterfaceManagerDetailsMapper sysInterfaceManagerDetailsMapper;
	
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
			//当前时间
			LocalDateTime date = LocalDateTime.now();
			//获取客户端ip
			String ip = this.getIp();
	        //获取该方法的id
//	        Integer id = sysInterfaceManagerMapper.queryByOpenName(className);
	        // result的值就是被拦截方法的返回值
	        Object result = new Object();
//	        if (verdictIp(ip).equals("no")) {
//	            throw exceptionManager.createByCode("INTE_IP_0001");
//	        }
//	        if (id == null) {
//	        	//方法未登记，不允许访问
//	            throw exceptionManager.createByCode("INTE_METH_0001");
//	        }
	        try{
	        	//保存请求参数 调用结果 调用时间 当前时间 ip地址 状态到日志 
	        	//请求参数
	        	String args = this.getArgs();
	        	//调用时间
	        	//LocalDateTime now  = LocalDateTime.now();
	        	Date now = new Date();
	        	//正常调用结果
	        	result = pjp.proceed();
	        	//正常 NORMAL("正常") ERRO("异常")
	        	String status = "NORMAL";
	        	//结束时间
	        	LocalDateTime overDate = LocalDateTime.now();
	        	String time = this.countTime(date, overDate);
	        	
	        	//入库
	        	SysInterfaceManagerDetails sysInterfaceManaDet = 
	        			new SysInterfaceManagerDetails();
	        	sysInterfaceManaDet.setManagerId(0);//暂无
	        	sysInterfaceManaDet.setTime(time);
	        	sysInterfaceManaDet.setIp(ip);
	        	sysInterfaceManaDet.setStatus(status);
	        	sysInterfaceManaDet.setRequest(args);
	        	sysInterfaceManaDet.setInfaceUrl(url);
	        	sysInterfaceManaDet.setStartTime(now);
	        	sysInterfaceManaDet.setResponse(result.toString());
	        	sysInterfaceManaDet.setCreateTime(new Date());
	        	sysInterfaceManaDet.setUpdateTime(new Date());
	        	sysInterfaceManaDet.setCreateBy("admin");
	        	sysInterfaceManaDet.setUpdateBy("admin");
	        	sysInterfaceManagerDetailsMapper.saveAndFlush(sysInterfaceManaDet);
	        	
	        	return result;
	        }catch(Throwable throwable){
	        	//保存请求参数 调用结果 调用时间 ip地址到日志
	        	//请求参数
	        	String args = this.getArgs();
	        	//调用时间
	        	Date now = new Date();
	        	//异常结果
	        	result = throwable.toString();
	        	String info = (String) result;
	        	//异常 NORMAL("正常") ERRO("异常")
	        	String status = null;
	        	
	        	//处理校验异常
	        	//跳过
	        	//处理自定义异常
	        	//跳过
	        	
	        	//结束时间
	        	LocalDateTime endTime = LocalDateTime.now();
	        	String time = this.countTime(date, endTime);
	        	//记录到接口管理中
	        	status = "ERRO";

	        	//入库
	        	SysInterfaceManagerDetails sysInterfaceManaDet = 
	        			new SysInterfaceManagerDetails();
	        	sysInterfaceManaDet.setManagerId(0);//暂无
	        	sysInterfaceManaDet.setTime(time);
	        	sysInterfaceManaDet.setIp(ip);
	        	sysInterfaceManaDet.setStatus(status);
	        	sysInterfaceManaDet.setRequest(args);
	        	sysInterfaceManaDet.setInfaceUrl(url);
	        	sysInterfaceManaDet.setStartTime(now);
	        	sysInterfaceManaDet.setResponse(info);
	        	sysInterfaceManaDet.setCreateTime(new Date());
	        	sysInterfaceManaDet.setUpdateTime(new Date());
	        	sysInterfaceManaDet.setCreateBy("admin");
	        	sysInterfaceManaDet.setUpdateBy("admin");
	        	sysInterfaceManagerDetailsMapper.saveAndFlush(sysInterfaceManaDet);
	        	
	        	return result;
	        }
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
     * @desc .获取请求ip
     */
	private String getIp() {
		//获取请求者的ip
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.
				getRequestAttributes()).getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
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

    /**
     * @author linlangleo
     * @desc .ip段判断
     */
//    private String verdictIp(String ip) {
//        //获取id
//        List<Integer> ids = sapApiLogAopService.urlByIp(ip);
//        if (ids != null && ids.size() != 0) {
//            for (Integer id : ids) {
//                Integer count = sapApiLogAopService.urlById(id, ip);
//                if (count != null) {
//                    return "yes";
//                }
//            }
//        }
//        return "no";
//    }
    
    /**
     * @author linlangleo
     * @desc 计算两个时间的毫秒差
     */
    private String countTime(LocalDateTime startTime, LocalDateTime endTime) {
    	long start = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    	long end = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    	long deduc = end -start;
    	
    	return String.valueOf(deduc);
    }
    
}