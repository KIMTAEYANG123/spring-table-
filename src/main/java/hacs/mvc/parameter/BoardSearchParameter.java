package hacs.mvc.parameter;

import lombok.Data;



/** 게시물 검색 파라미터
 * @author 태양
 *
 */
@Data
public class BoardSearchParameter {
	
	private String keyword;
	
	public BoardSearchParameter() {
		
	}
}
