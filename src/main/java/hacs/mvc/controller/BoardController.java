package hacs.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import hacs.configuration.exception.BaseException;
import hacs.configuration.http.BaseResponse;
import hacs.configuration.http.BaseResponseCode;
import hacs.configuration.web.bind.annotation.RequestConfig;
import hacs.mvc.domain.Board;
import hacs.mvc.domain.PageRequest;
import hacs.mvc.domain.PageRequestParameter;
import hacs.mvc.parameter.BoardSearchParameter;
import hacs.mvc.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//클래스 빠르게 찾을 때 컨트롤 + 쉬프트 + r

/** 게시판 컨트롤러
 * @author USER
 *
 */
@Controller
@RequestMapping("board")
@Api(tags = "게시판 api")
public class BoardController {

	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
 
	@Autowired
	BoardService boardService;
	
	/** 전체 주석 alt + shift + j
	 * 목록 리턴
	 * @return
	 */
	@GetMapping
	@ResponseBody
//	해당 api에 대한 설명 
	@ApiOperation(value ="목록 조회", notes = "게시물 번호에 해당하는 목록 정보를 조회할 수 있습니다.")
	public BaseResponse<List<Board>> getList(@ApiParam BoardSearchParameter param , @ApiParam PageRequest pageRequest){
		log.info("getList");
		PageRequestParameter<BoardSearchParameter> pageRequestParameter  = new PageRequestParameter<BoardSearchParameter>(pageRequest, param);
		return  new BaseResponse<List<Board>>(boardService.getList(pageRequestParameter));
	}
	
	/** 상세 정보
	 * @param boardSeq
	 * @return
	 */
//	url에 값이 파라미터로 들어가서 리턴 됨
	@GetMapping(value = "/{boardSeq}")
	@ResponseBody
//	스웨거에서 해당 api의 파라미터를 설명
	@ApiOperation(value ="상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1")
	})
	public  BaseResponse<Board> get(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL,new String[] {"게시물"} );
		}
		return  new BaseResponse<Board>(boardService.get(boardSeq));
	}

	/** 등록 및 수정 처리
	 * @param board
	 */
	@PostMapping
	@RequestConfig
	@ResponseBody
	@ApiOperation(value ="등록 수정", notes = "신규 게시물 저장 및 기존 게시물 수정을 할 수 있습니다.", produces = "multipart/form-data")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1"),
		@ApiImplicitParam(name = "title" , value = "게시물 제목", example = "spring"),
		@ApiImplicitParam(name = "contents" , value = "게시물 내용", example = "spring 강좌"),
	})
	public  BaseResponse<String> save (final Board board, @ApiParam(name="파일", value ="files", required = false) final MultipartFile files[]) {
		
		log.info("파일 정보 {} " , files );
		// 제목 필수 체크
		if(StringUtils.isEmpty(board.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED,new String[] {"title", "제목"} );
		}
		// 내용 필수 체크
		if(StringUtils.isEmpty(board.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED,new String[] {"contents", "제목"} );
		}
		 boolean boardSave = boardService.save(board,files);
//		 객체는 레퍼런수를 참조하기 때문에 가능
		 
		 if(boardSave) {
			 return  new BaseResponse<String>( Long.toString(board.getBoardSeq())+ " file : true" );
		 }else {
			 return  new BaseResponse<String>(Long.toString(board.getBoardSeq()));
		 }
		 
	}
	

	/**삭제 처리
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@RequestConfig
	@ApiOperation(value ="게시판 삭제", notes = "게시물 번호에 해당하는 게시물을 삭제합니다.")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "boardSeq" , value = "게시물 번호", example = "1"),
	})
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		Board board = boardService.get(boardSeq);
		if(board == null) {
			return  new BaseResponse<Boolean>(false);
		}
			
		boardService.delete(boardSeq);
		return new BaseResponse<Boolean>(true);
	}
	
	
	@ApiOperation(value ="대용량 등록처리1" , notes ="대용량 등록처리1")
	@PutMapping("/saveList1")
	public BaseResponse<Boolean> saveList1(){
		int count = 0;
		List<Board> list = new ArrayList<>();
		while(count < 500) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new Board(title,contents));
		}
		long start = System.currentTimeMillis();
		boardService.saveList1(list);
		long end = System.currentTimeMillis();
		log.info("실행시간 {}", (end-start) / 1000.0 );
		return new BaseResponse<Boolean>(true);
	}
	
	@ApiOperation(value ="대용량 등록처리2" , notes ="대용량 등록처리2")
	@PutMapping("/saveList2")
	public BaseResponse<Boolean> saveList2(){
		int count = 0;
		List<Board> list = new ArrayList<>();
		while(count < 500) {
			count++;
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new Board(title,contents));
		}
		long start = System.currentTimeMillis();
		boardService.saveList2(list);
		long end = System.currentTimeMillis();
		log.info("실행시간 {}", (end-start) / 1000.0 );
		return new BaseResponse<Boolean>(true);
	}
}
