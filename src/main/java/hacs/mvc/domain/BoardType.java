package hacs.mvc.domain;

/** 게시판 종류
 * @author USER
 *
 */
public enum BoardType implements BaseCodeLabelEnum {
	
	NOTICE("공지사항"),
	FAQ("자주묻는질문"),
	INQUIRY("1:1문의"),
	;


	private String code;
	private String label;
	
	BoardType(String label) {
//		enum에서 제공하는 name()메서드를 호출하면 notice , faq , inquiry 값이 들어감
		this.code = name();
		
		this.label = label;
	}
	
	@Override
	public String code() {
		return code;
	}

	@Override
	public String label() {
		return label;
	}

	
}
