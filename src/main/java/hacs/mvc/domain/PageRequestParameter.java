package hacs.mvc.domain;

import lombok.Data;


/**
 * 페이지 요청정보와 파라미터 정보
 * @author USER
 *
 * @param <T>
 */
@Data
public class PageRequestParameter<T> {
	
	private PageRequest pageRequest;
	private T parameter;
	
	public PageRequestParameter(PageRequest pageRequest, T parameter){
		this.pageRequest = pageRequest;
		this.parameter = parameter;
	}

}
