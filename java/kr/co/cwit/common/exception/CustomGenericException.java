package kr.co.cwit.common.exception;

public class CustomGenericException extends RuntimeException {
	private static final long serialVersionUID = 819156386612588013L;

	private String errCode;
	private String errStr;
	
	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrStr() {
		return errStr;
	}
	
	public void setErrStr(String errStr) {
		this.errStr = errStr;
	}

	public CustomGenericException(String errCode) {
		super("Custom Exception Occur CODE : " + errCode);
		this.errCode = errCode;
	}

	public CustomGenericException(String errCode, String errStr) {
		super("Custom Exception Occur CODE : " + errCode + ", Message : " + errStr);
		this.errCode = errCode;
		this.errStr = errStr;
	}
	
	// Warning Message 로 에러로그를 등록하지 않는다.
	public CustomGenericException(String msgGbn, String errCode, String errStr) {
		super("WARNING : "+msgGbn+" , Custom Exception Occur CODE : " + errCode + ", Message : " + errStr);
		this.errCode = errCode;
		this.errStr = errStr;
	}
}