package cn.sdormitory.common.exception;


import cn.sdormitory.common.api.IErrorCode;

/**
 * 自定义异常
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private long code = 500;
    
    public ApiException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public ApiException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public ApiException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public ApiException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public ApiException(IErrorCode exceptionEnum) {
		this.code = exceptionEnum.getCode();
		this.msg = exceptionEnum.getMessage();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}
