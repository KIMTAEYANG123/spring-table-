package hacs.configuration.http;

import lombok.Data;

@Data
public class BaseResponse<T> {
	
	private BaseResponseCode code;
	private String message;
	private T data;
	
	public BaseResponse(T data) {
		this.code = BaseResponseCode.SUCCESS;
		this.data = data;
		this.message = "저장되었습니다.";
	}

	public BaseResponse(BaseResponseCode responseCode, String message) {
		this.code = responseCode;
		this.message = message;
	}

}
