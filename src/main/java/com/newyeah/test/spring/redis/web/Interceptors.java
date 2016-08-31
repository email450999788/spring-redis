package com.newyeah.test.spring.redis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
@EnableWebMvc
public class Interceptors extends WebMvcAutoConfigurationAdapter {
	
	
	class WechatInterceptor implements HandlerInterceptor {

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
	            throws Exception {
	    	String echostr ;
	    	if(!StringUtils.isEmpty(echostr = request.getParameter("echostr"))){
	    		response.getOutputStream().print(echostr);
	    		response.getOutputStream().flush();
	    		System.out.println("刚配置.");
	    		return false ;
	    	}else{
	    		
	    	}
	        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	    }

	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
	            ModelAndView modelAndView) throws Exception {
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
	            throws Exception {
	    }

	}
	

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new WechatInterceptor()).addPathPatterns("/wechat");
        super.addInterceptors(registry);
	}

	
	
	
}
