package hacs.mvc.domain;

import lombok.Data;

@Data
public class Board {
	
	private long boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private String regDate;
	private boolean delYn;
	
	public Board() {
		
	}
	
	public Board(String title , String contents) {
		this.title = title;
		this.contents = contents;
	}
}
