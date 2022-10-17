package com.c702t.Cerveza.auth.config;

import com.c702t.Cerveza.auth.filter.JwtRequestFilter;
import com.c702t.Cerveza.auth.service.impl.UserDetailsCustomService;
import com.c702t.Cerveza.auth.utility.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsCustomService);
    }

    @Bean
    public PasswordEncoder PasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.cors().and()
                .csrf().disable()

                //Auth
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/**").permitAll()

                //Business
                .antMatchers(HttpMethod.POST, "/business/createBusiness").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/business/deleteBusiness").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/business/getById").hasAnyAuthority(RoleEnum.BUSINESS.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/business/getByFilters").hasAnyAuthority(RoleEnum.BUSINESS.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())

                //News
                .antMatchers(HttpMethod.POST, "/news/create").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/news/getAllNewsPage").hasAnyAuthority(RoleEnum.BUSINESS.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/news/delete").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())

                //Slides
                .antMatchers(HttpMethod.POST, "/slides/create").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/slides/getAllSlidesPage").hasAnyAuthority(RoleEnum.BUSINESS.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                .antMatchers(HttpMethod.DELETE, "/slides/delete").hasAuthority(RoleEnum.BUSINESS.getSimpleRoleName())
                .antMatchers(HttpMethod.GET, "/slides/getById").hasAnyAuthority(RoleEnum.BUSINESS.getSimpleRoleName(), RoleEnum.USER.getSimpleRoleName())
                //Swagger
                .antMatchers(publicEndpoint).permitAll()

                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}