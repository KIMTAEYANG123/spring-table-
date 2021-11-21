package hacs.mvc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import hacs.mvc.domain.Board;

@Repository
public interface BoardRepository {
	
	List<Board> getList();
	
	Board get(int boardSeq);
//	등록
	void save(Board board);
//	업데이트
	void update(Board board);
//	삭제
	void delete(int boardSeq);
	
	void saveList(Map<String,Object> paramMap);
}
