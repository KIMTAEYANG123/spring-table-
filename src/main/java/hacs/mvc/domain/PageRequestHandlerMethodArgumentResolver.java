package hacs.mvc.domain;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.bytebuddy.implementation.bytecode.ByteCodeAppender.Size;


/**
 * 쿼리 페이징 limit , offset 값을 자동계산하여 PageRequest 클래스 담아서 컨트롤러에서 받을 수 있게함.
 * @author 태양
 *
 */
public class PageRequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver{

	
	private static final Logger log = LoggerFactory.getLogger(PageRequestHandlerMethodArgumentResolver.class);
	
	private static final String DEFAULT_PARAM_PAGE = "page";
	private static final String DEFAULT_PARAM_SIZE = "size";
	private static final int DEFAULT_SIZE = 20;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		
		return PageRequest.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest(); 
		
		int page = org.apache.commons.lang3.math.NumberUtils.toInt(request.getParameter(DEFAULT_PARAM_PAGE),1);
		int offset = org.apache.commons.lang3.math.NumberUtils.toInt(request.getParameter(DEFAULT_PARAM_SIZE),DEFAULT_SIZE);
		
		int limit = (offset * page) - offset;
		log.info("page : {}", page);
		log.info("limit : {} offset  :{}",limit , offset );
		return new PageRequest(page, page, limit,offset);
	}
	

}
