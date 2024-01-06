package com.heeju.boardmvc.config;

import com.heeju.boardmvc.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log4j2
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService userDetailsService;

    // Log in filter
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("----------------configure----------------");

        http.headers().frameOptions().disable();

        // http.formLogin(Customizer.withDefaults());
        http.formLogin().loginPage("/member/login"); // Proceed to log in at the login screen

        http.csrf().disable();

        http.rememberMe()
                .key("1234")
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(userDetailsService)
                .tokenValiditySeconds(60*60*24*30); // sec*min*hr*days

        return http.build();
    }

    // Ignore security settings for static files
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        log.info("----------------web configure----------------");

        return (web) -> web.ignoring().requestMatchers(PathRequest.
                toStaticResources().atCommonLocations());
    }

    // PasswordEncoder Required for CustomeUserDetaileService to operate properly.
    // Then inject PasswordEncoder into CustomUserDetailService.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }




}
