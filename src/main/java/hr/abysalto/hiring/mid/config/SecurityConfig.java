package hr.abysalto.hiring.mid.config;

import hr.abysalto.hiring.mid.common.util.JwtUtils;
import hr.abysalto.hiring.mid.config.auth.JwtAuthenticationFilter;
import hr.abysalto.hiring.mid.config.auth.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/swagger-ui/**", "/v3/api-docs*/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        var jwtAuthenticationFilter = new JwtAuthenticationFilter(authManager, jwtUtils);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");
        var jwtAuthorizationFilter = new JwtAuthorizationFilter(authManager, jwtUtils);

        return http.csrf().disable()
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/api/login",
                            "/api/register"
                    ).permitAll()
                    .anyRequest().authenticated()
            )
            .addFilter(jwtAuthenticationFilter)
            .addFilter(jwtAuthorizationFilter)
            .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
