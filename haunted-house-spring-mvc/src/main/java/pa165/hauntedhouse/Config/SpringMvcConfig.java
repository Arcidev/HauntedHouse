package pa165.hauntedhouse.Config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import javax.validation.Validator;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import pa165.hauntedhouse.SampleData.SampleDataConfiguration;

/**
 * @author Andrej Dobes
 */
@EnableWebMvc
@Configuration
@Import(SampleDataConfiguration.class)
@ComponentScan(basePackages = "pa165.hauntedhouse.MVC.Controllers")
public class SpringMvcConfig extends WebMvcConfigurerAdapter {

    public static final String TEXTS = "Texts";

    /**
     * Maps the main page to a specific view
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
    
    /**
     * Maps resources
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    /**
     * Enables default Tomcat servlet
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * Provides mapping for views
     * @return the mapping of views
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Sets resources
     * @return source of the resources
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(TEXTS);
        return messageSource;
    }

    /**
     * @return JSR-303 Validator
     */
    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
    
    /**
     * Inicialize MultipartResolver for file uploads (Max 10MB)
     * 
     * @return MultipartResolver
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(500 * 1024);
        return multipartResolver;
    }
}
