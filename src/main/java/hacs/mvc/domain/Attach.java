package hacs.mvc.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Attach {

	/** 파일 번호 (PK) */
	private Long idx;

	/** 게시글 번호 (FK) */
	private Long boardSeq;

	/** 원본 파일명 */
	private String originalName;

	/** 저장 파일명 */
	private String saveName;

	/** 파일 크기 */
	private long size;
	
//	삭제 여부
	private char deleteYn;
//	삽입 시간
	private LocalDateTime insertTime;
//	삭제 시간
	private LocalDateTime deleteTime;
}
