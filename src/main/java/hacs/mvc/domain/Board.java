package hacs.mvc.domain;

import lombok.Data;

@Data
public class Board {
	private int boardSeq;
	private String title;
	private String contents;
	private String regDate;
}
