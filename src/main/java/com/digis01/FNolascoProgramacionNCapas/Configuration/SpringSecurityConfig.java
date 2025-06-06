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
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {
//        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(Directives.ALL));
        CookieClearingLogoutHandler cookies = new CookieClearingLogoutHandler("our-custom-cookie");

        http.authorizeHttpRequests(
                configure -> configure
                        .requestMatchers("/Usuario").hasAnyAuthority("Jefe", "Supervisor", "Jefe de empleados")
                        .requestMatchers("/Usuario", "/Usuario/CargaMasiva").hasAnyAuthority("Supervisor", "Jefe")
                        .requestMatchers(HttpMethod.GET, "/Usuario/**").hasAnyAuthority("Jefe de empleados", "Jefe")
                        .requestMatchers("/Usuario/**").hasAuthority("Jefe")
                        .requestMatchers("/Usuario/formEditable").hasAuthority("Jefe")
                        .requestMatchers("/logout").permitAll()
                        .anyRequest().authenticated())
                .formLogin(login -> login
                .loginPage("/Usuario/UserLogin")
                .loginProcessingUrl("/authenticateTheUser")
                .defaultSuccessUrl("/Usuario", true)
                .failureUrl("/Usuario/Error")
                .permitAll())
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/Usuario/Userlogin")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService jdbcDetailService(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailManager.setUsersByUsernameQuery("SELECT Username, Password, Enable from Usuarios WHERE Username = ?");

        jdbcUserDetailManager.setAuthoritiesByUsernameQuery("SELECT Username, NombreRoll FROM RolManager WHERE Username = ?");

        return jdbcUserDetailManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
