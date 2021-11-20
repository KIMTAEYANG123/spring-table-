package hacs.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/** mybatis 설정
 * @author 태양
 *
 */
@Configuration
@MapperScan(basePackages = "hacs.mvc.repository")
public class MybatisConfiguaration {

//	쉬프트 + 알트 + r 로 변수명을 한꺼번에 바꿀 수 있음
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource ) throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/sql/*.xml"));
		SqlSessionFactory  factory = factoryBean.getObject();
//		디비속성에 언더스코어로 된 것을 카멜케이스로 바꾸는 것 
		factory.getConfiguration().setMapUnderscoreToCamelCase(true);
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(@Autowired SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
