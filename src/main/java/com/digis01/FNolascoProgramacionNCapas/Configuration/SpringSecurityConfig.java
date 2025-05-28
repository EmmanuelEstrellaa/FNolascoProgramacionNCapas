package com.digis01.FNolascoProgramacionNCapas.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
        configure -> configure
                .requestMatchers("/Usuario", "/Usuario/CargaMasiva").hasAnyAuthority("Supervisor", "Jefe")
                .requestMatchers(HttpMethod.GET, "/Usuario/**").hasAnyAuthority("Jefe de empleados", "Jefe")
                .requestMatchers("/Usuario/**").hasAuthority("Jefe")
                .anyRequest().authenticated())
                .formLogin(login -> login.permitAll().defaultSuccessUrl("/Usuario"));

        return http.build();
    }
    
    
    @Bean
    public UserDetailsService jdbcDetailService(DataSource dataSource){
        
        JdbcUserDetailsManager jdbcUserDetailManager = new JdbcUserDetailsManager(dataSource);
        
        jdbcUserDetailManager.setUsersByUsernameQuery("SELECT Username, Password, Enable from Usuarios WHERE Username = ?");
        
        jdbcUserDetailManager.setAuthoritiesByUsernameQuery("SELECT Username, NombreRoll FROM RolManager WHERE Username = ?");
        
                
        return jdbcUserDetailManager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    

}
