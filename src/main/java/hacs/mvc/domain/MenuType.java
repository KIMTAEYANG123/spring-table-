package hacs.mvc.domain;

/** 메뉴 게시판 종류
 * @author USER
 *
 */
public enum MenuType {
	
	community(BoardType.COMMUNITY),
	notice(BoardType.NOTICE),
	faq(BoardType.FAQ),
	inquiry(BoardType.INQUIRY),
	;


	private BoardType boardType;
	
	MenuType(BoardType boardType) {
//		enum에서 제공하는 name()메서드를 호출하면 notice , faq , inquiry 값이 들어감
		this.boardType = boardType;

	}
	
	 public BoardType getBoardType() {
		return boardType;
	}
	
}
