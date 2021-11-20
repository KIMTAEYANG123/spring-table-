package hacs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swgger 설정
 * @author 태양
 *
 * pom.xml에 라이브러리 빌더 후 
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	
	@Bean
	public Docket docket() {
		ApiInfoBuilder info = new ApiInfoBuilder();
		info.title("API 서버 문서");
		info.description("API 서버 문서입니다.");
		
		Docket docket  = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(info.build());
		
//		api로 사용할 패키지를 선택
		ApiSelectorBuilder apis = docket.select().apis(RequestHandlerSelectors.basePackage("hacs.mvc.controller"));
//		패키지에 모든 유알엘
		apis.paths(PathSelectors.ant("/**"));
		
		return apis.build();
	}
}
