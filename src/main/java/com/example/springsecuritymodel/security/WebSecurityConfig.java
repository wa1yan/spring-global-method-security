package com.example.springsecuritymodel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

import static com.example.springsecuritymodel.security.SecurityRoles.*;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleHierarchy roleHierarchy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .expressionHandler(expressionHandler())
                .mvcMatchers("/","/home").permitAll()
                .mvcMatchers("/employees").hasRole(EMPLOYEES_PAG_VIEW)
                .mvcMatchers("/departments").hasRole(DEPARTMENTS_PAG_VIEW)
                .mvcMatchers("/customers").hasRole(CUSTOMERS_PAG_VIEW)
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("william")
                .password(encoder.encode("william"))
                .roles(SUPER_ADMIN)
                .and()
                .withUser("suzan")
                .password((encoder.encode("suzan")))
                .roles(EMPLOYEES_ADMIN)
                .and()
                .withUser("april")
                .password(encoder.encode("april"))
                .roles(DEPARTMENTS_PAG_VIEW,DEPARTMENTS_READ,DEPARTMENTS_CREATE)
                .and()
                .withUser("may")
                .password(encoder.encode("may"))
                .roles(CUSTOMERS_PAG_VIEW,CUSTOMERS_READ)
                .and()
                .withUser("june")
                .password(encoder.encode("june"))
                .roles();

    }

    private DefaultWebSecurityExpressionHandler expressionHandler(){
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy);
        return expressionHandler;
    }
}