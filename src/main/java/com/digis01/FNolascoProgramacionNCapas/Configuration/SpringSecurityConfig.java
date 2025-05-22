package com.digis01.FNolascoProgramacionNCapas.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public UserDetailsService user() {

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Carlos")
                .password("carlos123")
                .roles("admin")
                .build();
        UserDetails programador = User.withDefaultPasswordEncoder()
                .username("Ivan")
                .password("ivan123")
                .roles("programador")
                .build();
        UserDetails analista = User.withDefaultPasswordEncoder()
                .username("Jesus")
                .password("jesus123")
                .roles("analista")
                .build();

        return new InMemoryUserDetailsManager(admin, programador, analista);

    }

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/Usuario/Form/0").hasRole("programador")
                .requestMatchers("/Usuario/Form/0").hasRole("admin")
                .requestMatchers("/Usuario/formEditable").hasRole("programador")
                .requestMatchers("/Usuario/CargaMasiva").hasAnyRole("programador", "admin")
                .requestMatchers("/Usuario/Delete").hasRole("programador") 
                .requestMatchers("/Usuario/DeleteUsuario").hasRole("programador")
                .requestMatchers("/Usuario").hasAnyRole("programador", "admin", "analista")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .defaultSuccessUrl("/Usuario", true)
                .permitAll()
                )
                .logout(logout -> logout
                .permitAll()
                );

        return http.build();
    }

}
