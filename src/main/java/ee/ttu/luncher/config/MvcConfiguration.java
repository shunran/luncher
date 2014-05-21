package ee.ttu.luncher.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan(basePackages = "ee.ttu.luncher")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry aRegistry) {
		aRegistry.addResourceHandler("/s/*").addResourceLocations(
				"/WEB-INF/view/scripts/*");
		aRegistry.addResourceHandler("/c/*").addResourceLocations(
				"/WEB-INF/view/css/*");
		aRegistry.addResourceHandler("/i/*").addResourceLocations(
				"/WEB-INF/view/images/*");
		aRegistry.addResourceHandler("/WEB-INF/view/*").addResourceLocations(
				"WEB-INF/view/*");
		aRegistry.addResourceHandler("/favicon.ico").addResourceLocations(
				"/WEB-INF/view/images/favicon.ico");
	}
}
