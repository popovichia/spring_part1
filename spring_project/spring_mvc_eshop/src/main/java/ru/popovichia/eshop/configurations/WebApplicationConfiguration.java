package ru.popovichia.eshop.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@EnableWebMvc
@Configuration
@Import(DataBaseConfiguration.class)
@ComponentScan("ru.popovichia.eshop.controllers")
public class WebApplicationConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
        resourceHandlerRegistry
                .addResourceHandler("/css/*")
                .addResourceLocations("classpath:/css/");
    }
    
    @Bean(name = "templateResolver")
    public SpringResourceTemplateResolver getSpringResourceTemplateResolver() {
        SpringResourceTemplateResolver springResourceTemplateResolver
                = new SpringResourceTemplateResolver();
        springResourceTemplateResolver.setPrefix("/WEB-INF/views/");
        springResourceTemplateResolver.setSuffix(".html");
        return springResourceTemplateResolver;
    }

    @Bean(name = "templateEngine")
    public SpringTemplateEngine getSpringTemplateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(getSpringResourceTemplateResolver());
        return springTemplateEngine;
    }

    @Bean(name = "thymeleafViewResolver")
    public ThymeleafViewResolver getThymeleafViewResolver() {
        ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
        thymeleafViewResolver.setTemplateEngine(getSpringTemplateEngine());
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        return thymeleafViewResolver;
    }

    @Bean(name = "messageSource")
    public ResourceBundleMessageSource getResourceBundleMessageSource() {
        ResourceBundleMessageSource resourceBundleMessageSource
                = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("uitext");
        return resourceBundleMessageSource;
    }
    
}
