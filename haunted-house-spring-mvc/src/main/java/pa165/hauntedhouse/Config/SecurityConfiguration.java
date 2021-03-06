/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa165.hauntedhouse.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
/**
 *
 * @author Andrej Dobes
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
     
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
        .antMatchers(HttpMethod.GET, "/ability/edit/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.GET, "/spook/edit/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.GET, "/house/edit/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.GET, "/history/edit/**").access("hasRole('ADMIN')")
        .antMatchers(HttpMethod.POST, "/ability/edit/**").authenticated()
        .antMatchers(HttpMethod.POST, "/spook/edit/**").authenticated()
        .antMatchers(HttpMethod.POST, "/house/edit/**").authenticated()
        .antMatchers(HttpMethod.POST, "/history/edit/**").authenticated()
        .antMatchers("/ability/visible/**").access("hasRole('ADMIN')")
        .antMatchers("/house/visible/**").access("hasRole('ADMIN')")
        .antMatchers("/spook/visible/**").access("hasRole('ADMIN')")
        .antMatchers("/ability/removeSpook/**").access("hasRole('ADMIN')")
        .antMatchers("/ability/addSpook/**").access("hasRole('ADMIN')")
        .antMatchers("/house/removeSpook/**").access("hasRole('ADMIN')")
        .antMatchers("/house/addSpook/**").access("hasRole('ADMIN')")
        .antMatchers("/spook/removeAbility/**").access("hasRole('ADMIN')")
        .antMatchers("/spook/addAbility/**").access("hasRole('ADMIN')")
        .antMatchers("/ability/remove/**").access("hasRole('ADMIN')")
        .antMatchers("/spook/remove/**").access("hasRole('ADMIN')")
        .antMatchers("/house/remove/**").access("hasRole('ADMIN')")
        .antMatchers("/history/remove/**").access("hasRole('ADMIN')")
        .antMatchers("/ability/new/**").authenticated()
        .antMatchers("/spook/new/**").authenticated()
        .antMatchers("/house/new/**").authenticated()
        .antMatchers("/history/new/**").authenticated()
        .and().formLogin().loginPage("/login")
        .usernameParameter("email").passwordParameter("password")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/denied");
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
    }
    
    
}