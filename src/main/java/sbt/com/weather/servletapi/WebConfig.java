package sbt.com.weather.servletapi;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sbt.com.weather.servletapi.servlets.WeatherServlet;

@Configuration
public class WebConfig {

    @Bean
    public ServletRegistrationBean asimioTechServletRegistrationBean() {
        ServletRegistrationBean result = new ServletRegistrationBean(
                new WeatherServlet(), "/servlet/city/*");
        result.setName("weatherServlet");
        result.setOrder(1);
        return result;
    }
}
