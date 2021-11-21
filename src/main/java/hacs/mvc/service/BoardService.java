package hacs.mvc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hacs.mvc.domain.Board;
import hacs.mvc.repository.BoardRepository;
import jdk.internal.org.jline.utils.Log;


/**
 * 게시판 서비스
 * @author 태양
 *
 */
/**
 * @author USER
 *
 */
@Service
public class BoardService {
	
	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);

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
	
	
	/** 단순 반복문을 이용한 등록 처리
	 *  시간이 오래 걸림
	 * @param list
	 */
	public void saveList1(List<Board> list) {
		for(Board board : list) {
			repository.save(board);
		}
	}
	
	/** 100개씩 배열에 담아서 일괄 등록 처리.
	 * 	map에 넣고 반복처리를 디비에서 하면 빠름
	 * @param list
	 */
	public void saveList2(List<Board> list) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("boardList", list);
		log.info("여기는 왜 안 되요 ? {}" ,  paramMap.get("boardList"));
		repository.saveList(paramMap);
		
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
