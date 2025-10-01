package com.mottu.mototracker.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public JdbcUserDetailsManager users(DataSource dataSource) {
        JdbcUserDetailsManager mgr = new JdbcUserDetailsManager(dataSource);
        mgr.setUsersByUsernameQuery(
                "select username, password, enabled from users where username = ?"
        );
        mgr.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username = ?"
        );
        return mgr;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // H2 console em iframe
                .headers(h -> h.frameOptions(fr -> fr.sameOrigin()))
                // mantém CSRF, mas ignora para o H2 console
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .authorizeHttpRequests(auth -> auth
                        // público
                        .requestMatchers("/", "/login", "/register",
                                "/css/**", "/js/**", "/images/**",
                                "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**",
                                "/h2-console/**").permitAll()

                        // páginas novas
                        .requestMatchers("/mapa", "/patio").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/localizacoes").hasAnyRole("USER","ADMIN")

                        // módulo de motos (tudo para usuários logados)
                        .requestMatchers("/motos/**").hasAnyRole("USER","ADMIN")

                        // qualquer outra rota requer login
                        .anyRequest().authenticated()
                )
                .formLogin(fl -> fl
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/motos", true)   // vai direto para a listagem depois do login
                )
                .logout(Customizer.withDefaults());

        return http.build();
    }
}
