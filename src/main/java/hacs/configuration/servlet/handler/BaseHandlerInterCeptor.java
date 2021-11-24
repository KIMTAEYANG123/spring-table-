package hacs.configuration.servlet.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import hacs.configuration.exception.BaseException;
import hacs.configuration.http.BaseResponseCode;
import hacs.configuration.web.bind.annotation.RequestConfig;

public class BaseHandlerInterCeptor implements HandlerInterceptor{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("preHandle requestURI : {} " , request.getRequestURI());
		if(handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			logger.info("handler {}", handlerMethod);
			RequestConfig requestConfig = handlerMethod.getMethodAnnotation(RequestConfig.class);
			logger.info(" 리케스트컨피그 {}", requestConfig);
			if(requestConfig != null) {
//				로그인 체크가 필수인 경우
				if(!requestConfig.loginCheck()) {
					throw new BaseException(BaseResponseCode.LOGIN_REQUIRED, new String[] { request.getRequestURI()});
				}
			}
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
		logger.info("postHandle requestURI : {} " , request.getRequestURI());
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, @Nullable Exception arg3) throws Exception {
	}
}
