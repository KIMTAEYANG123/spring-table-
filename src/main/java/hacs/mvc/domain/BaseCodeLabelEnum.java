package hacs.mvc.domain;



/** 기본 CodeLabelEnum
 * @author 태양
 *
 */
public interface BaseCodeLabelEnum {
	
	/** 코드를 리턴
	 * @return
	 */
	String code();
	
	/** 라벨(코드명) 리턴
	 * @return
	 */
	String label();
}
