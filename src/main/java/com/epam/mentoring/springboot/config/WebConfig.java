package com.epam.mentoring.springboot.config;

import com.epam.mentoring.springboot.services.SocialNetworkService;
import com.epam.mentoring.springboot.services.impl.DefaultSocialNetworkServiceImpl;
import freemarker.template.utility.XmlEscape;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
public class WebConfig extends WebMvcConfigurerAdapter {

  @Autowired
  private Environment env;


  @Bean("messageSource")
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames("languages/messages", "messages");
    messageSource.setDefaultEncoding("UTF-8");
    return messageSource;
  }

  @Bean
  public LocaleResolver localeResolver() {
    return new CookieLocaleResolver();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
    dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
    dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
    dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    jdbcTemplate.setResultsMapCaseInsensitive(true);
    return jdbcTemplate;
  }

  @Bean
  public SimpleJdbcInsert simpleJdbcInsert(DataSource dataSource) {
    return new SimpleJdbcInsert(dataSource).withTableName("users");
  }

  @Bean
  public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Bean
  public SocialNetworkService socialNetworkService() {
    return new DefaultSocialNetworkServiceImpl();
  }

  @Bean
  public CookieThemeResolver themeResolver() {
    CookieThemeResolver resolver = new CookieThemeResolver();
    resolver.setDefaultThemeName("bright");
    resolver.setCookieName("my-theme-cookie");
    return resolver;
  }

  @Bean
  public ResourceBundleThemeSource themeSource() {
    ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
    themeSource.setDefaultEncoding("UTF-8");
    themeSource.setBasenamePrefix("themes.");
    return themeSource;
  }

  @Bean
  public ThemeChangeInterceptor themeChangeInterceptor() {
    ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
    interceptor.setParamName("theme");
    return interceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    localeChangeInterceptor.setParamName("lang");
    registry.addInterceptor(localeChangeInterceptor);
    registry.addInterceptor(themeChangeInterceptor());
  }

  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/themes/**").addResourceLocations("/themes/");
  }

}
