package hacs.mvc.parameter;



import java.util.List;

import hacs.mvc.domain.BoardType;
import lombok.Data;



/** 게시물 검색 파라미터
 * @author 태양
 *
 */
@Data
public class BoardSearchParameter {
	
	private String keyword;
	private List<BoardType> boardTypes;
	public BoardSearchParameter() {
		
	}
}
