package com.enix.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;

/**
 * Spring Security 設定
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 是否於 Heroku 禁用 http
     */
    @Value("${heroku.http.disabled:true}")
    boolean isHttpDisabled;

    /**
     * 設定 Request Filter 的檢查規則
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 設定強制 https 於 Heroku。
         * 可以參考 <a href="https://devcenter.heroku.com/articles/preparing-a-spring-boot-app-for-production-on-heroku#force-the-use-of-https">官方建議作法</a>。
         */
        if (isHttpDisabled) {
            log.info("Disable Http connection.");
            http.requiresChannel()
                    .requestMatchers(request -> request.getHeader("X-Forwarded-Proto") != null)
                    .requiresSecure();
        }
        http.csrf().disable();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
    }

}
