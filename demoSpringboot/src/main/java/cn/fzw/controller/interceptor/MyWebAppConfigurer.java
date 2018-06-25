package cn.fzw.controller.interceptor;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {
	@Override
	 public void addInterceptors(InterceptorRegistry registry) {
	        // 多个拦截器组成一个拦截器链
	        // addPathPatterns 用于添加拦截规则
	        // excludePathPatterns 用户排除拦截
			InterceptorRegistration ir=registry.addInterceptor(new LoginInterceptor());
			ir.addPathPatterns("/**");
			ir.excludePathPatterns("/login.jsp","/utils/getCode.action","/weixin/WeiXinTestConnection.action");
			super.addInterceptors(registry);
	    }
	 @Bean
	    public HttpMessageConverter<String> responseBodyConverter() {
	        StringHttpMessageConverter converter = new StringHttpMessageConverter(
	                Charset.forName("UTF-8"));
	        return converter;
	    }

	    @Override
	    public void configureMessageConverters(
	            List<HttpMessageConverter<?>> converters) {
	        super.configureMessageConverters(converters);
	        converters.add(responseBodyConverter());
	    }

	    @Override
	    public void configureContentNegotiation(
	            ContentNegotiationConfigurer configurer) {
	        configurer.favorPathExtension(false);
	    }

}
