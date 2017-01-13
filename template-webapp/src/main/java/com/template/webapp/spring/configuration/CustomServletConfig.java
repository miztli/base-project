/*
 * The MIT License
 *
 * Copyright 2016 Pivotal Software, Inc..
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.template.webapp.spring.configuration;

//import com.template.webapp.util.WebMappings;
//import java.util.HashMap;
//import java.util.Map;
//import org.springframework.boot.context.embedded.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author miztli
 */

//@Configuration
//public class CustomServletConfig {
//
//    public CustomServletConfig() {
//        super();
//    }
//    
//    
//    @Bean
//    public DispatcherServlet dispatcherServlet(){
//        return new DispatcherServlet();
//    }
//    
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean(){
//        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet(), WebMappings.APP_SERVLET_CONTEXT);
//        
//        final Map<String, String> params = new HashMap<>();
//            params.put("contextClass", "org.springframework.web.context.support.AnnotationConfigWebApplicationContext");
//            params.put("contextConfigLocation", "org.spring.sec2.spring");
//            params.put("dispatchOptionsRequest", "true");
//            
//            servletRegistrationBean.setInitParameters(params);
//            
//            servletRegistrationBean.setLoadOnStartup(1);
//            
//        return servletRegistrationBean;
//    }
    
//    @Bean
//    @Order(1)
//    public FilterRegistrationBean springSecurityFilterChain() {
//        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
//        final DelegatingFilterProxy delegatingFilterProxy = new DelegatingFilterProxy();
//        filterRegBean.setFilter(delegatingFilterProxy);
//        final List<String> urlPatterns = new ArrayList<String>();
//        urlPatterns.add("/*");
//        filterRegBean.setUrlPatterns(urlPatterns);
//        return filterRegBean;
//    }
//}
