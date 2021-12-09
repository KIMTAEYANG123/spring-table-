package hacs.configuration;

import java.util.List;
import java.util.Locale;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import hacs.configuration.servlet.handler.BaseHandlerInterCeptor;
import hacs.mvc.domain.BaseCodeLabelEnum;
import hacs.mvc.domain.PageRequestHandlerMethodArgumentResolver;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:/messages/message");
		source.setDefaultEncoding("UTF-8");
		source.setCacheSeconds(60);
		source.setDefaultLocale(Locale.KOREAN);
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
	
		@Bean
		public ObjectMapper objectMapper() {
			ObjectMapper objectMapper = new ObjectMapper();
			SimpleModule simpleModule = new SimpleModule();
			simpleModule.addSerializer(BaseCodeLabelEnum.class, new BaseCodeLabelEnumJsonSerializer());
			objectMapper.registerModule(simpleModule);
			return objectMapper;
		}
	
	  @Bean
	  public MappingJackson2JsonView mappingJackson2JsonView() {
	    MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
	    jsonView.setContentType(MediaType.APPLICATION_JSON_VALUE);
	    jsonView.setObjectMapper(objectMapper());
	    return jsonView;
	  }
	
	@Bean
	public BaseHandlerInterCeptor baseHandlerInterCeptor() {
		return new BaseHandlerInterCeptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(baseHandlerInterCeptor());
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new PageRequestHandlerMethodArgumentResolver());
	}
	
//	파일 업로드를 위한 빈 추가 
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
		multipartResolver.setMaxUploadSizePerFile(5 * 1024 * 1024); // 파일당 업로드 크기 제한 (5MB)
		return multipartResolver;
	}
	
	@Bean
	public FilterRegistrationBean<SitemeshConfiguration> sitemeshBean(){
		FilterRegistrationBean<SitemeshConfiguration> filter = new FilterRegistrationBean<>();
		filter.setFilter(new SitemeshConfiguration());
		return filter;
	}
	

	
}
