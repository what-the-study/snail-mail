package com.snailmail.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.DispatcherServlet;

@EnableJpaAuditing
@SpringBootApplication
public class SnailMailBackApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SnailMailBackApplication.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet) applicationContext.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

}
