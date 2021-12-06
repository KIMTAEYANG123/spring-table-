package hacs.mvc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import hacs.mvc.domain.Attach;
import hacs.mvc.repository.AttachRepository;
import hacs.mvc.util.FileUtils;

@Service
public class AttachService {

	
	private static final Logger log = LoggerFactory.getLogger(AttachService.class);

	@Autowired
	private  FileUtils fileUtils; 
	
	@Autowired
	private AttachRepository attachRepository;
	
	
	/** 등록 처리
	 * @param 
	 */
	public boolean save(MultipartFile[] multipartFiles) {
//		조회하여 리턴된 정보
		int result = 0;
		
		List<Attach> fileList = fileUtils.uploadFiles(multipartFiles);
		log.info("파일있나요 ? {}", fileList.isEmpty());
		
		if(CollectionUtils.isEmpty(fileList) == false) {
			result = attachRepository.insertAttach(fileList);
			if(result < 1) {
				return false;
			}
		}
		
		return result >= 1;
	}
}
