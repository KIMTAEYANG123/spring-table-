package hacs.configuration;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshConfiguration extends ConfigurableSiteMeshFilter{
	
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/board/community","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/board/notice","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/board/faq","/WEB-INF/views/decorator/default-layout.jsp");
		builder.addDecoratorPath("/board/inquiry","/WEB-INF/views/decorator/default-layout.jsp");
		super.applyCustomConfiguration(builder);
	}

}
