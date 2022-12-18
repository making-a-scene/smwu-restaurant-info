package smwu.smwurestaurantinfo.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;
import smwu.smwurestaurantinfo.service.MemberService;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final MemberService memberService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DefaultOAuth2UserService defaultOAuth2UserService() {
        return new OAuth2UserServiceImpl(memberService);
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
                .and()
                .oauth2Login()
                .loginPage("/oauth2/authorization/naver")
                .successHandler(new OAuth2LoginSuccessHandler())
                .userInfoEndpoint().userService(defaultOAuth2UserService());

        return http.build();

    }

}
