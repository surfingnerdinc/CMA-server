package surfingnerd.inc.sever.cfg;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import surfingnerd.inc.sever.enums.RoleEnum;
import surfingnerd.inc.sever.filters.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/auth/**")
                                                       .permitAll()
                                                       .requestMatchers("/api/v1/demo/hello")
                                                       .hasRole("EMPLOYEE")
                                                       .requestMatchers("/api/v1/demo/nothello")
                                                       .hasRole("ADMIN")
                                                       .anyRequest()
                                                       .authenticated())
                    .logout((logout) -> logout.logoutUrl("/api/v1/auth/logout")
                                              .logoutSuccessHandler((httpServletRequest, httpServletResponse,
                                                                     authentication) -> {
                                                  SecurityContextHolder.clearContext();
                                                  httpServletResponse.setStatus(200);
                                              }))

                    .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
