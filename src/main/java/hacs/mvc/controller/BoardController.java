package hacs.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hacs.mvc.domain.Board;
import hacs.mvc.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/** 게시판 컨트롤러
 * @author USER
 *
 */
@RestController
@RequestMapping("board")
@Api(tags = "게시판 api")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	/** 전체 주석 alt + shift + j
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
//	해당 api에 대한 설명 
	@ApiOperation(value ="목록 조회", notes = "게시물 번호에 해당하는 목록 정보를 조회할 수 있습니다.")
	public List<Board> getList(){
		return boardService.getList();
	}
	
	/** 상세 정보
	 * @param boardSeq
	 * @return
	 */
//	url에 값이 파라미터로 들어가서 리턴 됨
	@GetMapping(value = "/{boardSeq}")
	
//	스웨거에서 해당 api의 파라미터를 설명
	@ApiOperation(value ="상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1")
	})
	public Board get(@PathVariable int boardSeq) {
		return boardService.get(boardSeq);
	}

	/** 등록 및 수정 처리
	 * @param board
	 */
	@PutMapping
	@ApiOperation(value ="등록 수정", notes = "신규 게시물 저장 및 기존 게시물 수정을 할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title" , value = "게시물 제목", example = "spring"),
		@ApiImplicitParam(name = "contents" , value = "게시물 내용", example = "spring 강좌"),
	})
	public int save (Board board) {
		 boardService.save(board);
//		 객체는 레퍼런수를 참조하기 때문에 가능
		 return board.getBoardSeq();
	}


	/**삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value ="등록 수정", notes = "게시물 번호에 해당하는 게시물을 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1"),
	})
	public boolean delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return false;
		}
			
		boardService.delete(boardSeq);
		return true;
	}
}
