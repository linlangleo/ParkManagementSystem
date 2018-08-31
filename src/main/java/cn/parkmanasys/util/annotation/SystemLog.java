package cn.parkmanasys.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author linlangleo
 * @date 2018/6/29 15:38
 * @desc 自定义注解-(操作日志/接口日志).
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
	/**
     * 日志类型-(OperateLog:操作日志/InterfaceLog:接口日志)
     * @return
     */
	LogTypeEnum logType() ;
	
	/**
     * 操作描述，可为空
     * 操作日志用
     * @return
     */
	String operationDesc() default "";
}
