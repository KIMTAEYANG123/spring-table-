package hacs.mvc.domain;

import lombok.Data;

@Data
public class Board {
	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private String regDate;
	
	public Board() {
		
	}
	
	public Board(String title , String contents) {
		this.title = title;
		this.contents = contents;
	}
}
