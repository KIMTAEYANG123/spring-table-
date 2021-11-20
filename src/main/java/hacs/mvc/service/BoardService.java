package hacs.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hacs.mvc.domain.Board;
import hacs.mvc.repository.BoardRepository;


/**
 * 게시판 서비스
 * @author 태양
 *
 */
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository repository;
	
	
	/** 전체 주석 alt + shift + j
	 * 목록 리턴
	 * @return
	 */
	public List<Board> getList(){
		return repository.getList();
	}
	
	/** 상세 정보
	 * @param boardSeq
	 * @return
	 */
	public Board get(int boardSeq) {
		return repository.get(boardSeq);
	}

	/** 등록 처리
	 * @param board
	 */
	public void save(Board param) {
//		조회하여 리턴된 정보
		Board board = repository.get(param.getBoardSeq());
		if(board == null) {
			repository.save(param);
		}else {
			repository.update(param);
		}
		
		
	}


//	/**업데이트 처리
//	 * @param board
//	 */
//	public void update(Board board) {
//		repository.update(board);
//	}

	/**삭제 처리
	 * @param boardSeq
	 */
	public boolean delete(int boardSeq) {
//		조회하여 리턴된 정보
		repository.delete(boardSeq);
		return true;
	}
}
