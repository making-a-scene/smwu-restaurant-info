package smwu.smwurestaurantinfo.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 기본 정보는 Permit all
        // 리뷰 보는 건 회원만 가능
        // 리뷰 작성, 추천 등의 기여(?)는 숙대 인증받은 회원만 가능
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeHttpRequests()
                .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN","AUTHENTICATED", "UNAUTHENTICATED")
                .requestMatchers("/smin/**").hasRole("AUTHENTICATED")
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().oauth2Login();

        return http.build();

    }

}
