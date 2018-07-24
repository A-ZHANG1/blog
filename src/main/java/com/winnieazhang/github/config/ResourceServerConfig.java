package com.winnieazhang.github.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by Wayne.A.Z on 2018/7/20.
 */

@EnableResourceServer
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.headers().frameOptions().disable().and()
                .authorizeRequests()
                .antMatchers("/","/home","/login","/register").permitAll()
                .antMatchers("/private/**").authenticated()
                .antMatchers("/post").authenticated()
                .antMatchers("/oauth/*").permitAll();
    }
}
