package cn.parkmanasys.util.annotation;

/**
 * @author linlangleo
 * @date 2018/7/2 15:38
 * @desc 日志类型枚举
 */
public enum LogTypeEnum {
	OPERATION_LOG("OPERATION_LOG", "操作日志"),
	INTERFACE_LOG("INTERFACE_LOG", "接口日志")
	;

    private String status;
    private String message;

    LogTypeEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
