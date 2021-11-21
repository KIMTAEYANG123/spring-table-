package hacs.configuration.exception;

import hacs.configuration.http.BaseResponseCode;


//임포트 ctrl + shift + o


/**
 * 커스텀으로 예외처리를 하기 위해 만듦
 * 상황에 맞게 해당 클래스를 구현하기 위해
 * @author USER
 *
 */
public abstract class AbstractBaseException extends RuntimeException {
	
	private static final long serialVersionUID = 8342235231880246631L;
	
	protected BaseResponseCode responseCode;
	protected Object[] args;
	
	public AbstractBaseException() {
	}
	
	public AbstractBaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	public BaseResponseCode getResponseCode() {
		return responseCode;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
}

