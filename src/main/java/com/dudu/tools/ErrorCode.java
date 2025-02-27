package com.dudu.tools;

/**
 * @deprecated 异常枚举
 * */
public enum ErrorCode {
	ID_IS_NULL(400, "ID为空!"),
	ENT_CODE_IS_NULL(400,"企业编码为空!"),
	ADMIN_ACCOUNT_IS_NULL(400," 企业管理员账号为空!"),
	UUID_IS_NULL(400," UUID为空!"),
	MODIFY_FAIL(400,"修改失败!");

	private int httpStatus;
    private int code;
    private String message;

    // 构造方法
    private ErrorCode(int code, String message) {
    	this.setHttpStatus(200);
        this.setCode(code);
		this.setMessage(message);
    }
    private ErrorCode() {
    	this.setHttpStatus(httpStatus);
        this.setCode(code);
		this.setMessage(message);
    }
	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
