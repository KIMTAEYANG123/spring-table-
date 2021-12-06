package hacs.mvc.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import hacs.configuration.exception.AttachFileException;
import hacs.configuration.exception.BaseException;
import hacs.configuration.http.BaseResponseCode;
import hacs.mvc.domain.Attach;
import jdk.internal.org.jline.utils.Log;



/**
 * 파일 업로드 
 * @author 태양
 *
 */

// 개발자가 직접 작성한 클래스를 컨테이너에 등록할 떄
@Component
public class FileUtils {
	
	private static final Logger log = LoggerFactory.getLogger(FileUtils.class);

	/** 오늘 날짜 */
//	LocalDate.now() => result : 2021-11-25 현재 날짜
//	format 패턴 지정한 형태로 날짜가 나옴 211125
	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

	/** 업로드 경로 */
	/**
	 *  Paths 클래스는 get을 통해서 생성 가능
	 *  private final String uploadPath = Paths.get("C:\\develop\\upload\\today").toString(); 로도 가능
	 */
	private final String uploadPath = Paths.get("C:", "develop", "upload", today).toString();

	/**
	 * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
	 * 유일한 식별자를 생성할 수 있습니다. 숫자 일련번호를 사용한 식별자도 각각의 데이터를 구분하는데 충분하지만, 다른 데이터를 유추하기 쉽다는 단점이 있습니다.

		1. 업로드된 파일명의 중복을 방지하기 위해 파일명을 변경할 때 사용.
	
		2. 첨부파일 파일다운로드시 다른 파일을 예측하여 다운로드하는것을 방지하는데 사용.
	
		3. 일련번호 대신 유추하기 힘든 식별자를 사용하여 다른 컨텐츠의 임의 접근을 방지하는데 사용
	 * @return 랜덤 문자열
	 */
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 서버에 첨부 파일을 생성하고, 업로드 파일 목록 반환
	 * @param files    - 파일 Array
	 * @param boardIdx - 게시글 번호
	 * @return 업로드 파일 목록
	 */
	public List<Attach> uploadFiles(MultipartFile[] files, Long boardSeq) {

		
		/* 파일이 비어있으면 비어있는 리스트 반환 */
		if (files==null) {
			return Collections.emptyList();
		}

		/* 업로드 파일 정보를 담을 비어있는 리스트 */
		List<Attach> attachList = new ArrayList<>();

		/* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
		File dir = new File(uploadPath);
		if (dir.exists() == false) {
//			해당 경로의 폴더를 만든다.
			dir.mkdirs();
		}

		/* 파일 개수만큼 forEach 실행 */
		for (MultipartFile file : files) {
			try {
				/* 파일 확장자 */
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				/* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
				final String saveName = getRandomString() + "." + extension;

				/* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
				File target = new File(uploadPath, saveName);
				file.transferTo(target);

				/* 파일 정보 저장 */
				Attach attach = new Attach();
				attach.setBoardSeq(boardSeq);
				attach.setOriginalName(file.getOriginalFilename());
				attach.setSaveName(saveName);
				attach.setSize(file.getSize());

				/* 파일 정보 추가 */
				attachList.add(attach);

			} catch (IOException e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

			} catch (Exception e) {
				throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
			}
		} // end of for

		return attachList;
	}

}
