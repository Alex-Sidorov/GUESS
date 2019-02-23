package com.guess.configuration;

import com.guess.security.TokenAuthenticationProvider;
import com.guess.security.TokenFilter;
import com.guess.security.TokenValidator;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

import static com.guess.controller.AuthApi.basePath;
import static com.guess.controller.AuthApi.signInPath;
import static com.guess.controller.AuthApi.signUpPath;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final SecurityProblemSupport problemSupport;
    private final TokenAuthenticationProvider tokenAuthenticationProvider;
    private final TokenValidator tokenValidator;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .disable();

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .addFilterBefore(new TokenFilter(tokenValidator), BasicAuthenticationFilter.class);

        httpSecurity
                .authenticationProvider(tokenAuthenticationProvider);

        httpSecurity.exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport);

        httpSecurity.authorizeRequests()
                .antMatchers(signUpPath).permitAll()
                .antMatchers(signInPath).permitAll()
                .antMatchers(basePath + "/**").authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(tokenAuthenticationProvider);
    }

}
