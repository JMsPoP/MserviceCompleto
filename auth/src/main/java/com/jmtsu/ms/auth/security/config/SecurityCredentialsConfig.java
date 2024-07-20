package com.jmtsu.ms.auth.security.config;

import com.jmtsu.ms.auth.security.config.jwt.AuthEntryPointJwt;
import com.jmtsu.ms.auth.security.config.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.jmtsu.ms.core.property.JwtConfiguration;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
public class SecurityCredentialsConfig{
    private final AuthEntryPointJwt unauthorizedHandler;
   // private final UserDetailsService userDetailsService;
    private final JwtConfiguration jwtConfiguration;
    private final AuthenticationManagerBuilder authManagerBuilder;
/*
    @Override
        protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint((req, resp,e)->resp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .addFilter() not
                .authorizeRequests()
                .antMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                .antMatchers("/course/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated();
    }
    */
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

    http.cors(Customizer.withDefaults());
    http.csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authManagerBuilder.getOrBuild(), jwtConfiguration))
            .authorizeHttpRequests(auth -> auth.requestMatchers(jwtConfiguration.getLoginUrl()).permitAll()
                    .requestMatchers("/course/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated());

    return http.build();
}
/*
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
}
*/

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

