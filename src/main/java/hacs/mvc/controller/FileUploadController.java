package hacs.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import hacs.configuration.exception.BaseException;
import hacs.configuration.http.BaseResponse;
import hacs.configuration.http.BaseResponseCode;
import hacs.mvc.service.AttachService;

@RestController
@RequestMapping("/attach")
public class FileUploadController {

	
	private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

	
	private AttachService attachService;
	
	public FileUploadController(AttachService attachService) {
		this.attachService = attachService;
	}
	
	@PostMapping("/save")
	public BaseResponse<Boolean> save( @RequestParam("uploadFiles") MultipartFile[] multipartFiles  ) {
		log.info("사이즈 {}",multipartFiles.length);
		for(MultipartFile file : multipartFiles) {
			if(file == null || file.isEmpty()) {
				throw new BaseException(BaseResponseCode.DATA_IS_NULL,new String [] {"파일"});
			}
		}
		boolean save = attachService.save(multipartFiles);
		if(save) {
			return new BaseResponse<Boolean>(true);
		}else {
			return new BaseResponse<Boolean>(false);
		}
		
	}
}
