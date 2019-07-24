package com.stackroute.muzix.Config;

import org.apache.catalina.servlets.WebdavServlet;
import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class WebConfiguration {

    // bean for h2Servlet registration
    @Bean
    ServletRegistrationBean h2servletRegistration(){
    ServletRegistrationBean registrationBean=new ServletRegistrationBean(new WebServlet());
    registrationBean.addUrlMappings("/console/*");
    return registrationBean;
}
}
